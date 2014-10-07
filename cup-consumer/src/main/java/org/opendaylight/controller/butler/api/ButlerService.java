package org.opendaylight.controller.butler.api;

import java.util.concurrent.Future;

import org.opendaylight.yang.gen.v1.inocybe.rev141116.TeaType;
import org.opendaylight.yangtools.yang.common.RpcResult;

public interface ButlerService {
    Future<RpcResult<Void>> makeTea(NewsPaperType npt, Class<? extends TeaType> cup, int cupTemperature);
}
