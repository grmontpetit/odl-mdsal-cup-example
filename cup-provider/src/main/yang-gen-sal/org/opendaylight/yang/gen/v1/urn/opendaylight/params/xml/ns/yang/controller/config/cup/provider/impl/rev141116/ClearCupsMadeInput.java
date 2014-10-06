package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.cup.provider.impl.rev141116;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.rpc.context.rev130617.RpcContextRef;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>cup-provider-impl</b>
 * <br />(Source path: <i>META-INF/yang/cup-provider-impl.yang</i>):
 * <pre>
 * container input {
 *     leaf context-instance {
 *         type instance-identifier;
 *     }
 *     uses rpc-context-ref {
 *         refine (urn:opendaylight:params:xml:ns:yang:controller:config:cup-provider:impl?revision=2014-11-16)context-instance {
 *             leaf context-instance {
 *                 type instance-identifier;
 *             }
 *         }
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>cup-provider-impl/clear-cups-made/input</i>
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.cup.provider.impl.rev141116.ClearCupsMadeInputBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.cup.provider.impl.rev141116.ClearCupsMadeInputBuilder
 */
public interface ClearCupsMadeInput
    extends
    RpcContextRef,
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.cup.provider.impl.rev141116.ClearCupsMadeInput>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:opendaylight:params:xml:ns:yang:controller:config:cup-provider:impl","2014-11-16","input");;


}

