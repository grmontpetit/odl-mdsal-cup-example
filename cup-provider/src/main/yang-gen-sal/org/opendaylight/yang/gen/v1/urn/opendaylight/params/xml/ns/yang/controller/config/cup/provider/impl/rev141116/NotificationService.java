package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.cup.provider.impl.rev141116;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.rev130405.ServiceRef;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.cup.provider.impl.rev141116.CupProviderImplData;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>cup-provider-impl</b>
 * <br />(Source path: <i>META-INF/yang/cup-provider-impl.yang</i>):
 * <pre>
 * container notification-service {
 *     leaf type {
 *         type service-type-ref;
 *     }
 *     leaf name {
 *         type leafref;
 *     }
 *     uses service-ref {
 *         refine (urn:opendaylight:params:xml:ns:yang:controller:config:cup-provider:impl?revision=2014-11-16)type {
 *             leaf type {
 *                 type service-type-ref;
 *             }
 *         }
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>cup-provider-impl/notification-service</i>
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.cup.provider.impl.rev141116.NotificationServiceBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.cup.provider.impl.rev141116.NotificationServiceBuilder
 */
public interface NotificationService
    extends
    ChildOf<CupProviderImplData>,
    Augmentable<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.cup.provider.impl.rev141116.NotificationService>,
    ServiceRef
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:opendaylight:params:xml:ns:yang:controller:config:cup-provider:impl","2014-11-16","notification-service");;


}

