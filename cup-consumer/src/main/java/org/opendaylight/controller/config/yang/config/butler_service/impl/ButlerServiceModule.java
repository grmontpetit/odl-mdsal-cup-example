package org.opendaylight.controller.config.yang.config.butler_service.impl;

import java.util.concurrent.Future;

import org.opendaylight.controller.butler.api.ButlerService;
import org.opendaylight.controller.butler.api.NewsPaperType;
import org.opendaylight.controller.butler.impl.ButlerServiceImpl;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.CupService;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.TeaType;
import org.opendaylight.yangtools.yang.common.RpcResult;

public class ButlerServiceModule extends org.opendaylight.controller.config.yang.config.butler_service.impl.AbstractButlerServiceModule {
    public ButlerServiceModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
        super(identifier, dependencyResolver);
    }

    public ButlerServiceModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.controller.config.yang.config.butler_service.impl.ButlerServiceModule oldModule, java.lang.AutoCloseable oldInstance) {
        super(identifier, dependencyResolver, oldModule, oldInstance);
    }

    @Override
    public void customValidation() {
        // add custom validation form module attributes here.
    }

    @Override
    public java.lang.AutoCloseable createInstance() {

        CupService cupService = getRpcRegistryDependency().getRpcService(CupService.class);
        final ButlerServiceImpl butlerService = new ButlerServiceImpl(cupService);
        getBrokerDependency().registerConsumer(butlerService);

        /**
         * Register the ButlerService with JMX
         * and then close it in the AutoCloseable wrapper.
         */
        final ButlerServiceRuntimeRegistration runtimeReg =
                getRootRuntimeBeanRegistratorWrapper().register( butlerService );

        final class AutoCloseableButlerService implements ButlerService, AutoCloseable {

            @Override
            public void close() throws Exception {
                butlerService.close();
                runtimeReg.close();
            }

            @Override
            public Future<RpcResult<Void>> makeTea(NewsPaperType npt,
                    Class<? extends TeaType> teaType, int cupTemperature) {
                return butlerService.makeTea(npt, teaType, cupTemperature);
            }

        }
        AutoCloseable ret = new AutoCloseableButlerService();
        return ret;
    }

}
