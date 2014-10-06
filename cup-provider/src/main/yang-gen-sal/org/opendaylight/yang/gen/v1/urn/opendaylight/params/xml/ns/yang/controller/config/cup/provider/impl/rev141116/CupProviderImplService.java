package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.cup.provider.impl.rev141116;
import java.util.concurrent.Future;
import org.opendaylight.yangtools.yang.binding.RpcService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.cup.provider.impl.rev141116.ClearCupsMadeInput;
import org.opendaylight.yangtools.yang.common.RpcResult;


/**
 * Interface for implementing the following YANG RPCs defined in module <b>cup-provider-impl</b>
 * <br />(Source path: <i>META-INF/yang/cup-provider-impl.yang</i>):
 * <pre>
 * rpc clear-cups-made {
 *     "JMX call to clear the cups-made counter.";
 *     input {
 *         leaf context-instance {
 *             type instance-identifier;
 *         }
 *     }
 *     
 *     status CURRENT;
 * }
 * </pre>
 */
public interface CupProviderImplService
    extends
    RpcService
{




    /**
     * JMX call to clear the cups-made counter.
     */
    Future<RpcResult<java.lang.Void>> clearCupsMade(ClearCupsMadeInput input);

}

