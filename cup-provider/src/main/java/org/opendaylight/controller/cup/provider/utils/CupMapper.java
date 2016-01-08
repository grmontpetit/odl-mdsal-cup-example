/*
 * Copyright (c) 2016 Sniggel and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
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
