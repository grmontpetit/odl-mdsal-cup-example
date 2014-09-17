package org.opendaylight.controller;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.WriteTransaction;
import org.opendaylight.controller.md.sal.common.api.TransactionStatus;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.http.netconfcentral.org.ns.tea.time.rev691231.Cup;
import org.opendaylight.yang.gen.v1.http.netconfcentral.org.ns.tea.time.rev691231.Cup.CupStatus;
import org.opendaylight.yang.gen.v1.http.netconfcentral.org.ns.tea.time.rev691231.CupBuilder;
import org.opendaylight.yang.gen.v1.http.netconfcentral.org.ns.tea.time.rev691231.DisplayString;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class OpendaylightCup  implements AutoCloseable{
    //making this public because this unique ID is required later on in other classes.
    public static final InstanceIdentifier<Cup>  CUP_IID = InstanceIdentifier.builder(Cup.class).build();
    private static final Logger LOG = LoggerFactory.getLogger(OpendaylightCup.class);
    private static final DisplayString CUP_MANUFACTURER = new DisplayString("Opendaylight");
    private static final DisplayString CUP_MODEL_NUMBER = new DisplayString("Model 1 - Binding Aware");

    private DataBroker dataProvider;

    public OpendaylightCup(){}

    private Cup buildCup( CupStatus status ) {
        
        // note - we are simulating a device whose manufacture and model are
        // fixed (embedded) into the hardware.
        // This is why the manufacture and model number are hardcoded.
        return new CupBuilder().setCupManufacturer( CUP_MANUFACTURER )
                                   .setCupModelNumber( CUP_MODEL_NUMBER )
                                   .setCupStatus( status )
                                   .build();
    }

    public void setDataProvider( final DataBroker salDataProvider ) {
        this.dataProvider = salDataProvider;
        setCupStatusCold( null );
    }

    private void setCupStatusCold( final Function<Boolean,Void> resultCallback ) {
        
        WriteTransaction tx = dataProvider.newWriteOnlyTransaction();
        tx.put( LogicalDatastoreType.OPERATIONAL,CUP_IID, buildCup( CupStatus.Cold ) );
        
        ListenableFuture<RpcResult<TransactionStatus>> commitFuture = tx.commit();
        
        Futures.addCallback( commitFuture, new FutureCallback<RpcResult<TransactionStatus>>() {
            @Override
            public void onSuccess( RpcResult<TransactionStatus> result ) {
                if( result.getResult() != TransactionStatus.COMMITED ) {
                    LOG.error( "Failed to update cup status: " + result.getErrors() );
                }
                
                notifyCallback( result.getResult() == TransactionStatus.COMMITED );
            }
            
            @Override
            public void onFailure( Throwable t ) {
                // We shouldn't get an OptimisticLockFailedException (or any ex) as no
                // other component should be updating the operational state.
                LOG.error( "Failed to update toaster status", t );
                
                notifyCallback( false );
            }
            
            void notifyCallback( boolean result ) {
                if( resultCallback != null ) {
                    resultCallback.apply( result );
                }
            }
        } );
    }

    @Override
	public void close() throws Exception {
        if (dataProvider != null) {
            WriteTransaction t = dataProvider.newWriteOnlyTransaction();
            t.delete(LogicalDatastoreType.OPERATIONAL,CUP_IID);
            ListenableFuture<RpcResult<TransactionStatus>> future = t.commit();
            Futures.addCallback( future, new FutureCallback<RpcResult<TransactionStatus>>() {
                @Override
                public void onSuccess( RpcResult<TransactionStatus> result ) {
                    LOG.debug( "Delete Toaster commit result: " + result );
                }
                
                @Override
                public void onFailure( Throwable t ) {
                    LOG.error( "Delete of Toaster failed", t );
                }
            } );
        }
	}

}
