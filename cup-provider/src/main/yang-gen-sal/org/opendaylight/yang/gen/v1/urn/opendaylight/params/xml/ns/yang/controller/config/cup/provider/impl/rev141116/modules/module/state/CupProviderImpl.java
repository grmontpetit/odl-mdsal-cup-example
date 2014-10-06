package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.cup.provider.impl.rev141116.modules.module.state;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.rev130405.modules.module.State;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>cup-provider-impl</b>
 * <br />(Source path: <i>META-INF/yang/cup-provider-impl.yang</i>):
 * <pre>
 * case cup-provider-impl {
 *     leaf cups-made {
 *         type uint32;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>cup-provider-impl/modules/module/state/(urn:opendaylight:params:xml:ns:yang:controller:config:cup-provider:impl?revision=2014-11-16)cup-provider-impl</i>
 */
public interface CupProviderImpl
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.cup.provider.impl.rev141116.modules.module.state.CupProviderImpl>,
    State
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:opendaylight:params:xml:ns:yang:controller:config:cup-provider:impl","2014-11-16","cup-provider-impl");;

    java.lang.Long getCupsMade();

}

