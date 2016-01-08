package org.opendaylight.controller.cup.provider;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.DataObjectModification;
import org.opendaylight.controller.md.sal.binding.api.DataTreeIdentifier;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CupChangeHandler extends AbstractChangeHandler<Cup>{

    private static final Logger LOG = LoggerFactory.getLogger(CupChangeHandler.class);

    public CupChangeHandler(DataBroker dataProvider) {
        super(dataProvider, new DataTreeIdentifier<>(LogicalDatastoreType.CONFIGURATION,
                InstanceIdentifier.builder(Cup.class).build()));
    }

    @Override
    protected void onWrite(DataObjectModification<Cup> rootNode,
                          InstanceIdentifier<Cup> rootIdentifier) {
        LOG.info("Write operation on the Cup data tree.");
    }

    @Override
    protected void onDelete(DataObjectModification<Cup> rootNode,
                            InstanceIdentifier<Cup> rootIdentifier) {
        LOG.info("Delete on the Cup data tree.");
    }

    @Override
    protected void onSubtreeModified(DataObjectModification<Cup> rootNode,
                                     InstanceIdentifier<Cup> rootIdentifier) {
        LOG.info("Modification on the Cup data tree.");
    }
}
