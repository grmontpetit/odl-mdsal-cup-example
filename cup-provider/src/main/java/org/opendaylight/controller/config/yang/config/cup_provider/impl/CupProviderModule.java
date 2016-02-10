/*
 * Copyright (c) 2016 Sniggel and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.controller.config.yang.config.cup_provider.impl;

import org.opendaylight.controller.cup.provider.OpendaylightCup;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.CupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CupProviderModule extends org.opendaylight.controller.config.yang.config.cup_provider.impl.AbstractCupProviderModule {

    private static final Logger LOG = LoggerFactory.getLogger(CupProviderModule.class);

    public CupProviderModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
        super(identifier, dependencyResolver);
    }

    public CupProviderModule(final org.opendaylight.controller.config.api.ModuleIdentifier identifier,
                             final org.opendaylight.controller.config.api.DependencyResolver dependencyResolver,
                             final CupProviderModule oldModule,
                             final java.lang.AutoCloseable oldInstance) {
        super(identifier, dependencyResolver, oldModule, oldInstance);
    }

    @Override
    public void customValidation() {
        // add custom validation form module attributes here.
    }

    @Override
    public java.lang.AutoCloseable createInstance() {
        LOG.info("Cup Provider module started.");
        final OpendaylightCup opendaylightCup = new OpendaylightCup();
        getBrokerDependency().registerProvider(opendaylightCup);
        /**
         * Register the CupProviderModule via the getRpcRegistryDependency() method that is
         * in the AbstractCupProviderModule.
         * 
         * Effectively, this register the OpenDaylightCup with the RPC registry service.
         */
        final BindingAwareBroker.RpcRegistration<CupService> rpcRegistration = getRpcRegistryDependency()
                .addRpcImplementation(CupService.class, opendaylightCup);

        /**
         * Register the OpendaylightCup as the CupProviderRuntimeMXBean service. This is
         * done by via the CupProviderModule class, using the method CupProviderRuntimeRegistrator.
         */
        final CupProviderRuntimeRegistration runtimeReg = getRootRuntimeBeanRegistratorWrapper().register(
               opendaylightCup);

        // Wrap cup as AutoCloseable and close registrations to md-sal at
        // close(). The close method is where you would generally clean up thread pools
        // etc.
        final class AutoCloseableCup implements AutoCloseable {

            @Override
            public void close() throws Exception {
                // Close the cup, the close() method remove the data from the md-sal
                // tree via the dataBroker
                opendaylightCup.close();
                // Close the RPC methods
                rpcRegistration.close();
                // Close the OpendaylightCup that has been registered as
                // CupProviderRuntimeMXBean
                runtimeReg.close();
                LOG.info("CupProviderModule torn down.");
            }
        }
        return new AutoCloseableCup();
    }

}
