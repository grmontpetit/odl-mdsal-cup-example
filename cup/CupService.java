package org.opendaylight.yang.gen.v1.inocybe.rev141116;
import java.util.concurrent.Future;
import org.opendaylight.yangtools.yang.binding.RpcService;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput;


/**
 * Interface for implementing the following YANG RPCs defined in module <b>cup</b>
 * <br />(Source path: <i>META-INF/yang/cup.yang</i>):
 * <pre>
 * rpc cancel-cup {
 *     "Stop heating the cup, if any is being made.
 *               A 'resource-denied' error will be returned 
 *               if the cup service is disabled.";
 *     status CURRENT;
 * }
 * rpc heat-cup {
 *     "Heat the tea cup in the microwave. The cupHeated notification will be sent when the cup is heated.
 *             An 'in-use' error will be returned if the cup is already being made. A 'resource-denied' error will 
 *             be returned if the cup service is disabled.";
 *     input {
 *         leaf cupTemperature {
 *             type uint32;
 *         }
 *         leaf cupTeaType {
 *             type identityref;
 *         }
 *     }
 *     
 *     status CURRENT;
 * }
 * </pre>
 */
public interface CupService
    extends
    RpcService
{




    /**
     * Stop heating the cup, if any is being made. A 'resource-denied' error will be 
     * returned if the cup service is disabled.
     */
    Future<RpcResult<java.lang.Void>> cancelCup();
    
    /**
     * Heat the tea cup in the microwave. The cupHeated notification will be sent when 
     * the cup is heated. An 'in-use' error will be returned if the cup is already 
     * being made. A 'resource-denied' error will be returned if the cup service is 
     * disabled.
     */
    Future<RpcResult<java.lang.Void>> heatCup(HeatCupInput input);

}

