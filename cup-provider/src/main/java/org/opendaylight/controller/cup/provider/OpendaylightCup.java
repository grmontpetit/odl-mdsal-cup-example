package org.opendaylight.controller.cup.provider;

//import java.util.Arrays;
//import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import org.opendaylight.controller.config.yang.config.cup_provider.impl.CupProviderRuntimeMXBean;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.DataChangeListener;
import org.opendaylight.controller.md.sal.binding.api.ReadWriteTransaction;
import org.opendaylight.controller.md.sal.binding.api.WriteTransaction;
//import org.opendaylight.controller.md.sal.common.api.TransactionStatus;
import org.opendaylight.controller.md.sal.common.api.data.AsyncDataChangeEvent;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.controller.md.sal.common.api.data.OptimisticLockFailedException;
import org.opendaylight.controller.md.sal.common.api.data.TransactionCommitFailedException;
import org.opendaylight.controller.sal.binding.api.NotificationProviderService;

//import org.opendaylight.controller.sal.binding.api.data.DataChangeListener;
//import org.opendaylight.controller.sal.common.util.RpcErrors;
//import org.opendaylight.controller.sal.common.util.Rpcs;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup.CupStatus;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.CupBuilder;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.CupService;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.DisplayString;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.NoMoreCupsBuilder;
//import org.opendaylight.yangtools.concepts.Immutable;
//import org.opendaylight.yangtools.concepts.Path;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.RpcError;
//import org.opendaylight.yangtools.yang.common.RpcError.ErrorSeverity;
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

public class OpendaylightCup  implements CupService, AutoCloseable, DataChangeListener, CupProviderRuntimeMXBean{
    //making this public because this unique ID is required later on in other classes.
    public static final InstanceIdentifier<Cup>  CUP_IID = InstanceIdentifier.builder(Cup.class).build();
    private static final Logger LOG = LoggerFactory.getLogger(OpendaylightCup.class);
    private static final DisplayString CUP_MANUFACTURER = new DisplayString("Opendaylight");
    private static final DisplayString CUP_MODEL_NUMBER = new DisplayString("Model 1 - Binding Aware");

    private NotificationProviderService notificationProvider;
    private DataBroker dataProvider;
    private final ExecutorService executor;

    private final AtomicLong amountOfCupsInStock = new AtomicLong( 100 );

    private final AtomicLong cupTemperature = new AtomicLong( 1000 );

    private final AtomicLong cupsMade = new AtomicLong(0);

    // Thread safe holder for our temperature multiplier.
    private final AtomicLong cupTemperatureFactor = new AtomicLong( 1000 );

    // The following holds the Future for the current heat cup task.
    // This is used to cancel the current toast.
    private final AtomicReference<Future<?>> currentHeatCupTask = new AtomicReference<>();

    public OpendaylightCup(){
        executor = Executors.newFixedThreadPool(1);
    }

    private Cup buildCup( CupStatus status ) {
        // note - we are simulating a device whose manufacture and model are
        // fixed (embedded) into the hardware.
        // This is why the manufacture and model number are hardcoded.
        return new CupBuilder().setCupManufacturer( CUP_MANUFACTURER )
                                   .setCupModelNumber( CUP_MODEL_NUMBER )
                                   .setCupStatus( status )
                                   .build();
    }

    /**
     * Set the dataBroker
     * @param salDataProvider
     */
    public void setDataProvider( final DataBroker salDataProvider ) {
        this.dataProvider = salDataProvider;
        setCupStatusCold( null );
    }

    /**
     * Set the cup status in the MD-SAL tree using the
     * MD-SAL data broker. This is a write only transaction.
     * @param resultCallback
     */
    private void setCupStatusCold(final Function<Boolean, Void> resultCallback) {

        WriteTransaction tx = dataProvider.newWriteOnlyTransaction();
        tx.put( LogicalDatastoreType.OPERATIONAL, CUP_IID,
                buildCup(CupStatus.Cold));

        Futures.addCallback(tx.submit(), new FutureCallback<Void>() {
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

                notifyCallback(false);
            }

            void notifyCallback(final boolean result) {
                if (resultCallback != null) {
                    resultCallback.apply(result);
                }
            }
        });
    }

    /**
     * The close method implementation of autocloseable.
     */
    @Override
	public void close() throws Exception {
        executor.shutdown();
        
        if (dataProvider != null) {
            WriteTransaction tx = dataProvider.newWriteOnlyTransaction();
            tx.delete(LogicalDatastoreType.OPERATIONAL,CUP_IID);
            Futures.addCallback( tx.submit(), new FutureCallback<Void>() {
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
	}

    /**
     * Uses the yangtools.yang.common.RpcResultBuilder to
     * return a cancel cup Future.
     */
    @Override
    public Future<RpcResult<Void>> cancelCup() {
        System.out.println("Cancel the cup heating.");
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
        
        checkStatusAndHeatCup( input, futureResult, 2 );
   
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

        // Read the CupStatus and, if currently Cold, try to write the status to Heating.
        // If that succeeds, then we essentially have an exclusive lock and can proceed
        // to make the cup.
        /**
         * We create a ReadWriteTransaction by using the databroker.
         * Then, we read the status of the cup with getCupStatus() using the
         * databroker again. Once we have the status, we analyze it and
         * then databroker submit function is called to effectively change 
         * the cup status.
         * 
         * This all affects the MD-SAL tree, more specifically the part of the
         * tree that contain the cup (the nodes).
         */
        final ReadWriteTransaction tx = dataProvider.newReadWriteTransaction();
        ListenableFuture<Optional<Cup>> readFuture =
                                          tx.read( LogicalDatastoreType.OPERATIONAL, CUP_IID );

        final ListenableFuture<Void> commitFuture =
            Futures.transform( readFuture, new AsyncFunction<Optional<Cup>,Void>() {

                @Override
                public ListenableFuture<Void> apply(
                        final Optional<Cup> cupData ) throws Exception {

                    CupStatus cupStatus = CupStatus.Cold;
                    if( cupData.isPresent() ) {
                        cupStatus = cupData.get().getCupStatus();
                    }

                    LOG.debug( "Read cup status: {}", cupStatus );

                    if( cupStatus == CupStatus.Cold ) {

                        if( outOfCups() ) {
                            LOG.debug( "No more cups" );

                            return Futures.immediateFailedCheckedFuture(
                                    new TransactionCommitFailedException( "", makeNoMoreCupsError() ) );
                        }

                        LOG.debug( "Setting cup status to Down" );

                        // We're not currently making toast - try to update the status to Down
                        // to indicate we're going to make toast. This acts as a lock to prevent
                        // concurrent toasting.
                        tx.put( LogicalDatastoreType.OPERATIONAL, CUP_IID,
                                buildCup( CupStatus.Heating ) );
                        return tx.submit();
                    }

                    LOG.debug( "Oops - already making a cup!" );

                    // Return an error since we are already making cup. This will get
                    // propagated to the commitFuture below which will interpret the null
                    // TransactionStatus in the RpcResult as an error condition.
                    return Futures.immediateFailedCheckedFuture(
                            new TransactionCommitFailedException( "", makeCupInUseError() ) );
                }
        } );

        Futures.addCallback( commitFuture, new FutureCallback<Void>() {
            @Override
            public void onSuccess( final Void result ) {
                // OK to make cup
                currentHeatCupTask.set( executor.submit( new HeatCupTask( input, futureResult ) ) );
            }

            @Override
            public void onFailure( final Throwable ex ) {
                if( ex instanceof OptimisticLockFailedException ) {

                    // Another thread is likely trying to make toast simultaneously and updated the
                    // status before us. Try reading the status again - if another make toast is
                    // now in progress, we should get CupStatus.Cold and fail.

                    if( ( tries - 1 ) > 0 ) {
                        LOG.debug( "Got OptimisticLockFailedException - trying again" );

                        checkStatusAndHeatCup( input, futureResult, tries - 1 );
                    }
                    else {
                        futureResult.set( RpcResultBuilder.<Void> failed()
                                .withError( ErrorType.APPLICATION, ex.getMessage() ).build() );
                    }

                } else {

                    LOG.debug( "Failed to commit Cup status", ex );

                    // Probably already making toast.
                    futureResult.set( RpcResultBuilder.<Void> failed()
                            .withRpcErrors( ((TransactionCommitFailedException)ex).getErrorList() )
                            .build() );
                }
            }
        } );
    }// CheckStatusAndMakeCup

    /**
     * This is where the cup is heated. The callable method
     * is running as a thread but returns a value. In this
     * case, the HeatCupTask returns null. The function heats
     * the cup, when the cup is at the desired temprature, the
     * function returns null and the tea is ready to be brewed.
     * 
     * TODO Englishmen drinks the cup, then this method
     * sets the cup back to cold. The cup is automatically
     * filled with cold water ready to be heated in the
     * microwave.
     */
    private class HeatCupTask implements Callable<Void> {

        final HeatCupInput cupRequest;
        final SettableFuture<RpcResult<Void>> futureResult;

        public HeatCupTask( final HeatCupInput cupRequest,
                            final SettableFuture<RpcResult<Void>> futureResult ) {
            this.cupRequest = cupRequest;
            this.futureResult = futureResult;
        }

        @Override
        public Void call() {
            try
            {
                // make cup just sleeps for n seconds per 10 degrees level.
                long cupTemperature = OpendaylightCup.this.cupTemperature.get();
                //Thread.sleep(cupTemperature * cupRequest.getCupTemperature());
                System.out.println("Heating the cup for:"+(cupTemperature * cupRequest.getCupTemperature())+" mili seconds");
                Thread.sleep(cupTemperature * cupRequest.getCupTemperature());
                System.out.println("The cup is ready.");
            }
            catch( InterruptedException e ) {
                LOG.info( "Interrupted while making the toast" );
            }

            cupsMade.incrementAndGet();

            amountOfCupsInStock.getAndDecrement();
            if( outOfCups() ) {
                LOG.info( "No more cups!" );

                notificationProvider.publish( new NoMoreCupsBuilder().build() );
            }

            // Set the Cup status back to up - this essentially releases the toasting lock.
            // We can't clear the current toast task nor set the Future result until the
            // update has been committed so we pass a callback to be notified on completion.

            setCupStatusCold( new Function<Boolean,Void>() {
                @Override
                public Void apply( final Boolean result ) {

                    currentHeatCupTask.set( null );

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
     * @return true if there are no more cups, false otherwise.
     */
    private boolean outOfCups()
    {
        return amountOfCupsInStock.get() == 0;
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
     * This method is used to notify the OpendaylightCup when a data change
     * is made to the configuration.
     * 
     * Effectively, the cup subtree node is modified through restconf
     * and the onDataChanged is triggered. We check if the changed dataObject is
     * from the cup subtree, if so we get the value of the temperature.
     * 
     * If the temprature from the node is not null, then we change the temperature of
     * the cup class with the subtree temperature.
     */
    @Override
    public void onDataChanged(  // AsyncDataChangedEvent interface
                                // public interface AsyncDataChangeEvent<P extends Path<P>, D> extends Immutable{} 
            AsyncDataChangeEvent<InstanceIdentifier<?>, DataObject> change) {
        System.out.println("Data change event triggered on cup");
        DataObject dataObject = change.getUpdatedSubtree();
        if( dataObject instanceof Cup )
        {
            Cup cup = (Cup) dataObject;
            Long temperature = cup.getCupTemperatureFactor();
            if( temperature != null )
            {
                System.out.println("Cup temperature (longValue): "+temperature.longValue());
                cupTemperatureFactor.set( temperature );
            }
        }
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
	    LOG.info("clearCupsMade");
	    System.out.println("Clearing the ammount of cups that have been made.");
	    cupsMade.set(0);
	}
}
