package org.opendaylight.controller.config.yang.config.cup_provider.impl;

import org.opendaylight.controller.OpendaylightCup;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;

public class CupProviderModule extends org.opendaylight.controller.config.yang.config.cup_provider.impl.AbstractCupProviderModule {
    public CupProviderModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
        super(identifier, dependencyResolver);
    }

    public CupProviderModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.controller.config.yang.config.cup_provider.impl.CupProviderModule oldModule, java.lang.AutoCloseable oldInstance) {
        super(identifier, dependencyResolver, oldModule, oldInstance);
    }

    @Override
    public void customValidation() {
        // add custom validation form module attributes here.
    }

    @Override
    public java.lang.AutoCloseable createInstance() {
        final OpendaylightCup opendaylightCup = new OpendaylightCup();
        
        DataBroker dataBrokerService = getDataBrokerDependency();
        opendaylightCup.setDataProvider(dataBrokerService);
        
        // Wrap cup as AutoCloseable and close registrations to md-sal at
        // close(). The close method is where you would generally clean up thread pools
        // etc.
        final class AutoCloseableCup implements AutoCloseable {

            @Override
            public void close() throws Exception {
                opendaylightCup.close();
            }
        }
        
        return new AutoCloseableCup();
    }

}
