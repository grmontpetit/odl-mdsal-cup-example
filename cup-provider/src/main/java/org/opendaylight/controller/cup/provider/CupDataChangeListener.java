/*
 * Copyright (c) 2016 Sniggel and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.controller.cup.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.opendaylight.controller.cup.provider.utils.CupMapper;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.DataChangeListener;
import org.opendaylight.controller.md.sal.binding.api.WriteTransaction;
import org.opendaylight.controller.md.sal.common.api.data.AsyncDataChangeEvent;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.controller.md.sal.common.api.data.AsyncDataBroker.DataChangeScope;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.CupBuilder;
import org.opendaylight.yangtools.concepts.ListenerRegistration;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CupDataChangeListener implements DataChangeListener, AutoCloseable {

    private DataBroker dataBroker;
    private static final Logger LOG = LoggerFactory.getLogger(CupDataChangeListener.class);
    private Map<String, ListenerRegistration<DataChangeListener>> listeners;

    public CupDataChangeListener(DataBroker dataBroker) {
        this.dataBroker = dataBroker;
        listeners = new HashMap<String, ListenerRegistration<DataChangeListener>>();
        /**
         * The CupDataChangeListener class registers itself
         * as a dataChangeListener. In this case, it wants to be
         * notified when the Configuration tree is modified
         * on the Cup Iid part of it.
         */
        ListenerRegistration<DataChangeListener> cupDataChangeListener = dataBroker.registerDataChangeListener(
                LogicalDatastoreType.CONFIGURATION, 
                CupMapper.getCupIid(), this, DataChangeScope.SUBTREE);
        listeners.put("cup", cupDataChangeListener);
    }

    @Override
    public void close() throws Exception {
        LOG.info("CupDataChangeListener closed.");
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
    public void onDataChanged(
            AsyncDataChangeEvent<InstanceIdentifier<?>, DataObject> changes) {
        create(changes.getCreatedData());
        update(changes.getUpdatedData());
        delete(changes);
    }

    private void delete(
            AsyncDataChangeEvent<InstanceIdentifier<?>, DataObject> changes) {
    }

    /**
     * Changes the cup temperature factor and
     * copy the change over the operational tree.
     * @param changes
     */
    private void update(
            Map<InstanceIdentifier<?>, DataObject> changes) {
        for (Entry<InstanceIdentifier<?>, DataObject> updated : changes.entrySet()) {
            if (updated.getValue() != null && updated.getValue() instanceof Cup) {
                Cup oldCup = (Cup) updated.getValue();
                Long temperature = oldCup.getCupTemperatureFactor();
                if( temperature != null ) {
                    WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();
                    Cup newCup = new CupBuilder()
                                  .setCupTemperatureFactor(temperature)
                                  .setCupManufacturer(CupConstants.CUP_MANUFACTURER)
                                  .setCupModelNumber(CupConstants.CUP_MODEL_NUMBER)
                                  .setCupStatus(oldCup.getCupStatus())
                                  .build();
                    transaction.put(LogicalDatastoreType.OPERATIONAL,
                                    CupMapper.getCupIid(),
                                    newCup);
                    transaction.submit();
                }
            }
        }
    }

    private void create(
            Map<InstanceIdentifier<?>, DataObject> changes) {
    }
}
