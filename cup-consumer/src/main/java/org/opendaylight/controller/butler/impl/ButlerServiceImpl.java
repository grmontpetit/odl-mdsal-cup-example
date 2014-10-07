package org.opendaylight.controller.butler.impl;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.opendaylight.controller.butler.api.ButlerService;
import org.opendaylight.controller.butler.api.NewsPaperType;
import org.opendaylight.controller.config.yang.config.butler_service.impl.ButlerServiceRuntimeMXBean;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.BlackTea;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.CupListener;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.CupService;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInputBuilder;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.NoMoreCups;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.TeaType;
import org.opendaylight.yangtools.yang.common.RpcError;
import org.opendaylight.yangtools.yang.common.RpcError.ErrorType;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.JdkFutureAdapters;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class ButlerServiceImpl implements ButlerService, ButlerServiceRuntimeMXBean, CupListener{

    private static final Logger log = LoggerFactory.getLogger(ButlerServiceImpl.class);

    private final CupService cup;

    private final ListeningExecutorService executor = 
                                   MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
    private volatile boolean outOfCups;

    public ButlerServiceImpl(CupService cup){
        this.cup = cup;
    }

    @Override
    public Future<RpcResult<Void>> makeTea(NewsPaperType npt,
            Class<? extends TeaType> teaType, int cupTemperature) {
        // Call heatCup and use JdkFutureAdapters to convert the Future to a ListenableFuture,
        // The OpendaylightCup impl already returns a ListenableFuture so the conversion is
        // actually a no-op.
        ListenableFuture<RpcResult<Void>> heatCupFuture = JdkFutureAdapters.listenInPoolThread(
                heatCup(teaType, cupTemperature), executor);

        ListenableFuture<RpcResult<Void>> fetchNewsPapersFuture = fetchNewsPapers(npt);

        /**
         * Combines the two listenable futures as a list of RpcResults
         */
        ListenableFuture<List<RpcResult<Void>>> combinedFutures = 
                Futures.allAsList( ImmutableList.of(heatCupFuture, fetchNewsPapersFuture) );

        /**
         * Transform the list of the two RpcResult into one result.
         */
        return Futures.transform( combinedFutures,
                new AsyncFunction<List<RpcResult<Void>>, RpcResult<Void>>() {
                    @Override
                    public ListenableFuture<RpcResult<Void>> apply(
                            List<RpcResult<Void>> results) throws Exception {
                        boolean atLeastOneSucceeded = false;
                        Builder<RpcError> errorList = ImmutableList.builder();
                        for (RpcResult<Void> result: results){
                            if( result.isSuccessful() ) {
                                atLeastOneSucceeded = true;
                            }

                            if( result.getErrors() != null ) {
                                errorList.addAll( result.getErrors() );
                            }
                        }
                        
                        return Futures.immediateFuture(
                                RpcResultBuilder.<Void> status(atLeastOneSucceeded)
                                .withRpcErrors( errorList.build()).build());
                    }  
                });
    }

    /**
     * 
     * @param npt (newsPaperType)
     * @return ListenableFuture (return a success by default)
     */
    private ListenableFuture<RpcResult<Void>> fetchNewsPapers(NewsPaperType npt) {

        return executor.submit(new Callable<RpcResult<Void>>(){

            @Override
            public RpcResult<Void> call() throws Exception {

                // we don't actually do anything here, just return a successful result.
                return RpcResultBuilder.<Void> success().build();
            }
        });
    }

    /**
     * 
     * @param teaType
     * @param cupTemperature
     * @return Future of the RpcResult
     */
    private Future<RpcResult<Void>> heatCup(Class<? extends TeaType> teaType,
            int cupTemperature) {

        if (outOfCups){
            log.info("We don't have any cups left, but we have newspapers");
            return Futures.immediateFuture(RpcResultBuilder.<Void> success()
                    .withWarning(ErrorType.APPLICATION, "partial-operation",
                                 "Out of cups but I can serve you the newspapers").build() );
        }

        // Access the CupService to heat a cup
        
        HeatCupInput cupInput = new HeatCupInputBuilder()
            .setCupTemperature((long) cupTemperature)
            .setCupTeaType(teaType)
            .build();

        return cup.heatCup(cupInput);
    }

    @Override
    public Boolean makeBlackTeaWithLapresse() {
        try {
            // This call has to block since we must return a result to the JMX client.
            RpcResult<Void> result = makeTea( NewsPaperType.LAPRESSE, BlackTea.class, 85 ).get();
            if( result.isSuccessful() ) {
                log.info( "makeTea succeeded" );
            } else {
                log.warn( "makeTea failed: " + result.getErrors() );
            }
  
            return result.isSuccessful();
  
        } catch( InterruptedException | ExecutionException e ) {
            log.warn( "An error occurred while serving the tea: " + e );
        }
  
        return Boolean.FALSE;
    }

    @Override
    public void onCupsRestocked(CupsRestocked notification) {
        log.info( "CupRestocked notification - amountOfCups: " + notification.getAmountOfCups() );
        outOfCups = false;
    }

    @Override
    public void onNoMoreCups(NoMoreCups notification) {
        log.info("Out of clean cups");
        outOfCups = true;
    }
}
