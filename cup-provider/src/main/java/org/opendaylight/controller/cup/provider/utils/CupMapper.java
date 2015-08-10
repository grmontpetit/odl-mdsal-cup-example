package org.opendaylight.controller.cup.provider.utils;

import org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;

public class CupMapper {

    public CupMapper() throws InstantiationException {
        throw new InstantiationException("This class should not be instanciated.");
    }

    public static final InstanceIdentifier<Cup> getCupIid() {
        return InstanceIdentifier.builder(Cup.class)
                                    .build();
    }

}
