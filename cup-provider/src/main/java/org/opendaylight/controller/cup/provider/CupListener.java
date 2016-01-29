/*
 * Copyright (c) 2016 Sniggel and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.controller.cup.provider;

import org.opendaylight.controller.cup.provider.utils.CupMapper;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.DataObjectModification;
import org.opendaylight.controller.md.sal.binding.api.DataTreeIdentifier;
import org.opendaylight.controller.md.sal.binding.api.WriteTransaction;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.CupBuilder;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CupListener extends AbstractChangeHandler<Cup> {

    private static final Logger LOG = LoggerFactory.getLogger(CupListener.class);

    public CupListener(DataBroker dataProvider, DataTreeIdentifier<Cup> pointOfInterest) {
        super(dataProvider, pointOfInterest);
    }

    @Override
    protected void onWrite(DataObjectModification<Cup> rootNode, InstanceIdentifier<Cup> rootIdentifier) {
        LOG.info("Write is not used in CUP module.");
    }

    @Override
    protected void onDelete(DataObjectModification<Cup> rootNode, InstanceIdentifier<Cup> rootIdentifier) {
        LOG.info("Delete is not implemented for CUP module.");
    }

    @Override
    protected void onSubtreeModified(DataObjectModification<Cup> rootNode, InstanceIdentifier<Cup> rootIdentifier) {
        Cup oldCup = rootNode.getDataBefore();
        Long temperature = oldCup.getCupTemperatureFactor();
        if( temperature != null ) {
            WriteTransaction transaction = dataProvider.newWriteOnlyTransaction();
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
