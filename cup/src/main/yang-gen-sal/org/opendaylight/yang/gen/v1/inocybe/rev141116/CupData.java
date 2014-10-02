package org.opendaylight.yang.gen.v1.inocybe.rev141116;
import org.opendaylight.yangtools.yang.binding.DataRoot;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup;


/**
 * YANG version of the TEA-MIB.
 * <p>This class represents the following YANG schema fragment defined in module <b>cup</b>
 * <br />Source path: <i>META-INF/yang/cup.yang</i>):
 * <pre>
 * module cup {
 *     yang-version 1;
 *     namespace "inocybe";
 *     prefix "tea";
 *     revision 2014-11-16 {
 *         description "YANG version of the TEA-MIB.
 *         ";
 *     }
 *     container cup {
 *         leaf cupManufacturer {
 *             type DisplayString;
 *         }
 *         leaf cupModelNumber {
 *             type DisplayString;
 *         }
 *         leaf cupStatus {
 *             type enumeration;
 *         }
 *         leaf cupTemperatureFactor {
 *             type uint32;
 *         }
 *     }
 *     identity black-tea {
 *         base "()IdentitySchemaNodeImpl[base=null, qname=(inocybe?revision=2014-11-16)tea-type]";
 *         description
 *             "Black tea.";
 *         status CURRENT;
 *     }
 *     identity green-tea {
 *         base "()IdentitySchemaNodeImpl[base=null, qname=(inocybe?revision=2014-11-16)tea-type]";
 *         description
 *             "Green tea.";
 *         status CURRENT;
 *     }
 *     identity red-tea {
 *         base "()IdentitySchemaNodeImpl[base=null, qname=(inocybe?revision=2014-11-16)tea-type]";
 *         description
 *             "Red tea";
 *         status CURRENT;
 *     }
 *     identity tea-type {
 *         description
 *             "Tea that can be used in the tea cup.";
 *         status CURRENT;
 *     }
 *     notification noMoreCups {
 *         description
 *             "Indicates that there are no available cups to make tea.";
 *         status CURRENT;
 *     }
 *     rpc cancel-cup {
 *         "Stop heating the cup, if any is being made.
 *                   A 'resource-denied' error will be returned 
 *                   if the cup service is disabled.";
 *         status CURRENT;
 *     }
 *     rpc heat-cup {
 *         "Heat the tea cup in the microwave. The cupHeated notification will be sent when the cup is heated.
 *                 An 'in-use' error will be returned if the cup is already being made. A 'resource-denied' error will 
 *                 be returned if the cup service is disabled.";
 *         input {
 *             leaf cupTemperature {
 *                 type uint32;
 *             }
 *             leaf cupTeaType {
 *                 type identityref;
 *             }
 *         }
 *         
 *         status CURRENT;
 *     }
 * }
 * </pre>
 */
public interface CupData
    extends
    DataRoot
{




    /**
     * Top-level container for all toaster database objects.
     */
    Cup getCup();

}

