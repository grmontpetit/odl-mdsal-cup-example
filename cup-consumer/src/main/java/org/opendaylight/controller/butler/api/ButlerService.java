/*
 * Copyright (c) 2016 Sniggel and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.controller.butler.api;

import java.util.concurrent.Future;

import org.opendaylight.yang.gen.v1.inocybe.rev141116.TeaType;
import org.opendaylight.yangtools.yang.common.RpcResult;

public interface ButlerService {
    Future<RpcResult<Void>> makeTea(NewsPaperType npt, Class<? extends TeaType> cup, int cupTemperature);
}
