package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116;
import java.util.concurrent.Future;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput;
import org.opendaylight.yangtools.yang.binding.RpcService;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseInput;


/**
 * Interface for implementing the following YANG RPCs defined in module <b>butler-service-impl</b>
 * <br />(Source path: <i>META-INF/yang/butler-service-impl.yang</i>):
 * <pre>
 * rpc make-black-tea-with-lapresse {
 *     "Shortcut JMX call to serve a cup of tea with the newspapers";
 *     input {
 *         leaf context-instance {
 *             type instance-identifier;
 *         }
 *     }
 *     
 *     output {
 *         leaf result {
 *             type boolean;
 *         }
 *     }
 *     status CURRENT;
 * }
 * </pre>
 */
public interface ButlerServiceImplService
    extends
    RpcService
{




    /**
     * Shortcut JMX call to serve a cup of tea with the newspapers
     */
    Future<RpcResult<MakeBlackTeaWithLapresseOutput>> makeBlackTeaWithLapresse(MakeBlackTeaWithLapresseInput input);

}

