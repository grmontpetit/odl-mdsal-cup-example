package org.opendaylight.controller.cup.provider;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import org.opendaylight.controller.config.yang.config.cup_provider.impl.CupProviderRuntimeMXBean;
import org.opendaylight.controller.cup.provider.utils.CupMapper;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.ReadWriteTransaction;
import org.opendaylight.controller.md.sal.binding.api.WriteTransaction;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.controller.md.sal.common.api.data.OptimisticLockFailedException;
import org.opendaylight.controller.md.sal.common.api.data.TransactionCommitFailedException;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.ProviderContext;
import org.opendaylight.controller.sal.binding.api.BindingAwareProvider;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup.CupStatus;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.CupBuilder;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.CupService;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.RpcError;
import org.opendaylight.yangtools.yang.common.RpcError.ErrorType;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

public class OpendaylightCup  implements  AutoCloseable, BindingAwareProvider, CupService, CupProviderRuntimeMXBean{

    private static final Logger LOG = LoggerFactory.getLogger(OpendaylightCup.class);
    private DataBroker dataBroker;
    private final ExecutorService executor;
    private final AtomicLong amountOfCupsInStock = new AtomicLong(100);
    private final AtomicLong cupsMade = new AtomicLong(0);
    // Thread safe holder for our temperature multiplier.
    private final AtomicLong cupTemperatureFactor = new AtomicLong(1000);
    // The following holds the Future for the current heat cup task..
    private final AtomicReference<Future<?>> currentHeatCupTask = new AtomicReference<>();
    private CupDataChangeListener cupDataChangeListener;
    // Don't want to create transactions everytime
    private volatile Status cupStatus = Status.Cold;

    public OpendaylightCup() {
        executor = Executors.newFixedThreadPool(1);
    }

    /**
     * Override from BindingAwareProvider
     */
    @Override
    public void onSessionInitiated(ProviderContext session) {
        LOG.info("OpendaylightCup session initiated.");
        dataBroker =  session.getSALService(DataBroker.class);
        cupStatus.setStatus(0);
        syncCupWithDataStore(LogicalDatastoreType.OPERATIONAL, CupMapper.getCupIid(), buildCup());
        syncCupWithDataStore(LogicalDatastoreType.CONFIGURATION, CupMapper.getCupIid(), buildCup());
    }

    /**
     * Used by the CupProviderModule to initialize
     * the dataChange listener.
     */
    public void init() {
        cupDataChangeListener = new CupDataChangeListener(dataBroker);
    }

    /**
     * Default function to build a cup. It will use the class global
     * variables.
     * @return A built cup object using CupBuilder().build()
     */
    private Cup buildCup() {
        return new CupBuilder()
                .setCupManufacturer(CupConstants.CUP_MANUFACTURER)
                .setCupModelNumber(CupConstants.CUP_MODEL_NUMBER)
                .setAmmountOfCupsInStock(amountOfCupsInStock.get())
                .setAmmountOfCupsMade(cupsMade.get())
                .setCupStatus(CupStatus.values()[cupStatus.getStatus()])
                .build();
    }
    /**
     * Set the cup status in the MD-SAL tree using the
     * MD-SAL data broker. This is a write only transaction.
     * @param resultCallback
     */
    private void setCupStatusCold(final Function<Boolean, Void> resultCallback) {

        cupStatus.setStatus(0);
        WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();
        transaction.put( LogicalDatastoreType.OPERATIONAL,
                         CupMapper.getCupIid(),
                         buildCup());

        Futures.addCallback(transaction.submit(), new FutureCallback<Void>() {
            @Override
            public void onSuccess(final Void result) {
                notifyCallback(true);
            }

            @Override
            public void onFailure(final Throwable t) {
                // We shouldn't get an OptimisticLockFailedException (or any ex)
                // as no
                // other component should be updating the operational state.
                LOG.error("Failed to update cup status", t);
                resetCupData();
                notifyCallback(false);
            }

            void notifyCallback(final boolean result) {
                if (resultCallback != null) {
                    resultCallback.apply(result);
                }
            }
        });
    }

    private void resetCupData() {
        cupStatus.setStatus(0);
        amountOfCupsInStock.incrementAndGet();
        cupsMade.incrementAndGet();
        syncCupWithDataStore(LogicalDatastoreType.OPERATIONAL,
                             CupMapper.getCupIid(),
                             buildCup());
    }

    /**
     * Uses the yangtools.yang.common.RpcResultBuilder to
     * return a cancel cup Future.
     */
    @Override
    public Future<RpcResult<Void>> cancelCup() {
        LOG.info("Cancel called on the Cup heating.");
        Future<?> current = currentHeatCupTask.getAndSet(null);
        if (current != null){
            current.cancel(true);
        }
        return Futures.immediateFuture(RpcResultBuilder.<Void> success().build());
    }

    /**
     * Use the checkStatusAndHeatCup method to create a cup Future by
     * reading the CupStatus and, if currently Cold, try to write the status to Heating.
     * if that succeeds, then we essentially have an exclusive lock and can proceed
     * to make the cup.
     */
    @Override
    public Future<RpcResult<Void>> heatCup(HeatCupInput input) {
        final SettableFuture<RpcResult<Void>> futureResult = SettableFuture.create();
        checkStatusAndHeatCup(input, futureResult, 2);
        return futureResult;
    }

    /**
     * Read the CupStatus and, if currently Cold, try to write the status to Heating.
     * If that succeeds, then we essentially have an exclusive lock and can proceed
     * to make the cup.
     * @param input
     * @param futureResult
     * @param tries
     */
    private void checkStatusAndHeatCup(final HeatCupInput input,
                                       final SettableFuture<RpcResult<Void>> futureResult,
                                       final int tries) {

        /*  Read the CupStatus and, if currently Cold, try to write the status to Heating.
         *  If that succeeds, then we essentially have an exclusive lock and can proceed
         *  to make the cup.
         *  We create a ReadWriteTransaction by using the databroker.
         *  Then, we read the status of the cup with getCupStatus() using the
         *  databroker again. Once we have the status, we analyze it and
         *  then databroker submit function is called to effectively change 
         *  the cup status.
         *
         *  This all affects the MD-SAL tree, more specifically the part of the
         *  tree that contain the cup (the nodes).
         */
        final ReadWriteTransaction transaction = dataBroker.newReadWriteTransaction();
        ListenableFuture<Optional<Cup>> readFuture =
                transaction.read(LogicalDatastoreType.OPERATIONAL, CupMapper.getCupIid());

        final ListenableFuture<Void> commitFuture =
            Futures.transform(readFuture, new AsyncFunction<Optional<Cup>,Void>() {

                @Override
                public ListenableFuture<Void> apply(
                        final Optional<Cup> cupData) throws Exception {

                    if(cupData.isPresent()) {
                        cupStatus.setStatus(cupData.get().getCupStatus().getIntValue());
                        amountOfCupsInStock.set(cupData.get().getAmmountOfCupsInStock());
                        cupsMade.set(cupData.get().getAmmountOfCupsMade());
                    } else {
                        throw new Exception("Error reading Cup data from the store.");
                    }
                    LOG.debug("Read cup status: {}", cupStatus);
                    if(cupStatus.getStatus() == CupStatus.Cold.getIntValue()) {
                        if(amountOfCupsInStock.get() == 0) {
                            LOG.debug("No more cups.");
                            return Futures.immediateFailedCheckedFuture(
                                    new TransactionCommitFailedException("", makeNoMoreCupsError()));
                        }
                        LOG.info("Heating the Cup...");
                        cupStatus.setStatus(CupStatus.Heating.getIntValue());
                        transaction.put(LogicalDatastoreType.OPERATIONAL,
                                        CupMapper.getCupIid(),
                                        buildCup());
                        return transaction.submit();
                    }
                    LOG.debug( "Oops - already making a cup!" );
                    // Return an error since we are already making cup. This will get
                    // propagated to the commitFuture below which will interpret the null
                    // TransactionStatus in the RpcResult as an error condition.
                    return Futures.immediateFailedCheckedFuture(
                            new TransactionCommitFailedException("", makeCupInUseError()));
                }
        });

        Futures.addCallback(commitFuture, new FutureCallback<Void>() {
            @Override
            public void onSuccess(final Void result) {
                // OK to make cup
                currentHeatCupTask.set(executor.submit(new HeatCupTask(input,
                                                        futureResult)));
            }

            @Override
            public void onFailure( final Throwable ex ) {
                if( ex instanceof OptimisticLockFailedException ) {
                    // Another thread is likely trying to heat a cup simultaneously and updated the
                    // status before us. Try reading the status again - if another heat cup is
                    // now in progress, we should get CupStatus.Cold and fail.
                    if( ( tries - 1 ) > 0 ) {
                        LOG.debug( "Got OptimisticLockFailedException - trying again" );
                        checkStatusAndHeatCup(input, futureResult, tries - 1);
                    }
                    else {
                        futureResult.set(RpcResultBuilder.<Void> failed()
                                .withError(ErrorType.APPLICATION, ex.getMessage()).build());
                    }
                } else {
                    LOG.debug( "Failed to commit Cup status", ex );
                    // Probably already heating a cup.
                    futureResult.set(RpcResultBuilder.<Void> failed()
                            .withRpcErrors(((TransactionCommitFailedException)ex).getErrorList())
                            .build());
                }
            }
        });
    }// CheckStatusAndMakeCup

    /**
     * This is where the cup is heated. The callable method
     * is running as a thread but returns a value. In this
     * case, the HeatCupTask returns null. The function heats
     * the cup, when the cup is at the desired temprature, the
     * function returns null and the tea is ready to be brewed.
     * 
     * Englishmen drinks the cup, then this method
     * sets the cup back to cold. The cup is automatically
     * filled with cold water ready to be heated in the
     * microwave.
     */
    private class HeatCupTask implements Callable<Void> {

        final HeatCupInput cupRequest;
        final SettableFuture<RpcResult<Void>> futureResult;

        public HeatCupTask( final HeatCupInput cupRequest,
                            final SettableFuture<RpcResult<Void>> futureResult) {
            this.cupRequest = cupRequest;
            this.futureResult = futureResult;
        }

        @Override
        public Void call() {
            try {
                // make cup just sleeps for n seconds per 10 degrees level.
                long cupTemperature = CupConstants.CUP_TEMPERATURE.get();
                Thread.sleep(cupTemperature * cupRequest.getCupTemperature());
            } catch( InterruptedException e ) {
                LOG.info( "Interrupted while heating a cup." );
            }
            amountOfCupsInStock.decrementAndGet();
            cupsMade.incrementAndGet();
            cupStatus.setStatus(CupStatus.Heating.getIntValue());
            syncCupWithDataStore(LogicalDatastoreType.OPERATIONAL,
                                 CupMapper.getCupIid(),
                                 buildCup());

            // Set the Cup status back to up - this essentially releases the cup heating lock.
            // We can't clear the current heat cup task nor set the Future result until the
            // update has been committed so we pass a callback to be notified on completion.

            setCupStatusCold( new Function<Boolean,Void>() {
                @Override
                public Void apply( final Boolean result ) {

                    currentHeatCupTask.set(null);

                    LOG.debug("Cup ready");

                    futureResult.set( RpcResultBuilder.<Void>success().build() );

                    return null;
                }
            } );

            return null;
        }
    }

    /**
     * 
     * @return The RPC error message in case the RPC service
     * is not available.
     */
    private RpcError makeNoMoreCupsError() {
        return RpcResultBuilder.newError( ErrorType.APPLICATION, "resource-denied",
                "No more cups", "out-of-stock", null, null );
    }

    /**
     * 
     * @return The RPC error in use.
     */
    private RpcError makeCupInUseError() {
        return RpcResultBuilder.newWarning( ErrorType.APPLICATION, "in-use",
                "Cup is busy (in-use)", null, null, null );
    }

    /**
     * Accessor method implemented from the CupProviderRuntimeMXBean interface.
     */
    @Override
    public Long getCupsMade() {
        return cupsMade.get();
    }

    /**
    * JMX RPC call implemented from the CupProviderRuntimeMXBean interface.
    */
    @Override
    public void clearCupsMade() {
        LOG.info("Clear the ammount of cups made.");
        cupsMade.set(0);
        syncCupWithDataStore(LogicalDatastoreType.OPERATIONAL,
                             CupMapper.getCupIid(),
                             buildCup());
    }

    @Override
    public Future<RpcResult<Void>> restockCups(RestockCupsInput input) {
        LOG.info( "restockCups: " + input );
        Long current = amountOfCupsInStock.get();
        amountOfCupsInStock.set(current + input.getAmountOfCupsToClean());
        syncCupWithDataStore(LogicalDatastoreType.OPERATIONAL,
                             CupMapper.getCupIid(),
                             buildCup());
        return Futures.immediateFuture( RpcResultBuilder.<Void> success().build() );
    }

    public void setCupTemperatureFactor(Long temperature) {
        this.cupTemperatureFactor.set(temperature);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void syncCupWithDataStore(final LogicalDatastoreType store,
                                      InstanceIdentifier iid,
                                      final DataObject object) {
        WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();
        transaction.put(store, iid, object);
        // Perform the tx.submit asynchronously
        Futures.addCallback(transaction.submit(), new FutureCallback<Void>() {
            @Override
            public void onSuccess(final Void result) {
                LOG.info("SyncStore {} with object {} succeeded", store, object);
            }
            @Override
            public void onFailure(final Throwable throwable)  {
                LOG.error("SyncStore {} with object {} failed", store, object);
            }
        });
    }

    /**
     * The close method implementation of autocloseable.
     * We delete any data that has been submitted using the
     * Cup InstanceIdentifier.
     */
    @Override
    public void close() throws Exception {
        executor.shutdown();
        if (dataBroker != null) {
            WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();
            transaction.delete(LogicalDatastoreType.OPERATIONAL,CupMapper.getCupIid());
            Futures.addCallback( transaction.submit(), new FutureCallback<Void>() {
                @Override
                public void onSuccess( final Void result ) {
                    LOG.debug( "Delete cup commit result: " + result );
                }

                @Override
                public void onFailure( final Throwable t ) {
                    LOG.error( "Delete of Cup failed", t );
                }
            } );
        }
        
        if (cupDataChangeListener != null) {
            cupDataChangeListener.close();
        }
    }
}
