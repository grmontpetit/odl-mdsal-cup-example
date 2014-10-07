package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.cup.provider.impl.rev141116;
import org.opendaylight.yangtools.yang.binding.DataRoot;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.cup.provider.impl.rev141116.NotificationService;


/**
 * This module contains the base YANG definitions for cup-provider impl 
 * implementation.
 * <p>This class represents the following YANG schema fragment defined in module <b>cup-provider-impl</b>
 * <br />Source path: <i>META-INF/yang/cup-provider-impl.yang</i>):
 * <pre>
 * module cup-provider-impl {
 *     yang-version 1;
 *     namespace "urn:opendaylight:params:xml:ns:yang:controller:config:cup-provider:impl";
 *     prefix "cup-provider-impl";
 *     import opendaylight-md-sal-binding { prefix "mdsal"; }
 *     
 *     import config { prefix "config"; }
 *     
 *     import rpc-context { prefix "rpcx"; }
 *     revision 2014-11-16 {
 *         description "This module contains the base YANG definitions for cup-provider impl 
 *                     implementation.
 *         ";
 *     }
 *     container notification-service {
 *         leaf type {
 *             type service-type-ref;
 *         }
 *         leaf name {
 *             type leafref;
 *         }
 *         uses service-ref {
 *             refine (urn:opendaylight:params:xml:ns:yang:controller:config:cup-provider:impl?revision=2014-11-16)type {
 *                 leaf type {
 *                     type service-type-ref;
 *                 }
 *             }
 *         }
 *     }
 *     augment \(urn:opendaylight:params:xml:ns:yang:controller:config)modules\(urn:opendaylight:params:xml:ns:yang:controller:config)module\(urn:opendaylight:params:xml:ns:yang:controller:config)configuration {
 *         status CURRENT;
 *         case cup-provider-impl {
 *             container data-broker {
 *                 leaf type {
 *                     type service-type-ref;
 *                 }
 *                 leaf name {
 *                     type leafref;
 *                 }
 *                 uses service-ref {
 *                     refine (urn:opendaylight:params:xml:ns:yang:controller:config:cup-provider:impl?revision=2014-11-16)type {
 *                         leaf type {
 *                             type service-type-ref;
 *                         }
 *                     }
 *                 }
 *             }
 *             container rpc-registry {
 *                 leaf type {
 *                     type service-type-ref;
 *                 }
 *                 leaf name {
 *                     type leafref;
 *                 }
 *                 uses service-ref {
 *                     refine (urn:opendaylight:params:xml:ns:yang:controller:config:cup-provider:impl?revision=2014-11-16)type {
 *                         leaf type {
 *                             type service-type-ref;
 *                         }
 *                     }
 *                 }
 *             }
 *         }
 *     }
 *     augment \(urn:opendaylight:params:xml:ns:yang:controller:config)modules\(urn:opendaylight:params:xml:ns:yang:controller:config)module\(urn:opendaylight:params:xml:ns:yang:controller:config)state {
 *         status CURRENT;
 *         case cup-provider-impl {
 *             leaf cups-made {
 *                 type uint32;
 *             }
 *         }
 *     }
 *     identity clear-cups-made-rpc {
 *         status CURRENT;
 *     }
 *     identity cup-provider-impl {
 *         base "()IdentitySchemaNodeImpl[base=null, qname=(urn:opendaylight:params:xml:ns:yang:controller:config?revision=2013-04-05)module-type]";
 *         status CURRENT;
 *     }
 *     rpc clear-cups-made {
 *         "JMX call to clear the cups-made counter.";
 *         input {
 *             leaf context-instance {
 *                 type instance-identifier;
 *             }
 *         }
 *         
 *         status CURRENT;
 *     }
 * }
 * </pre>
 */
public interface CupProviderImplData
    extends
    DataRoot
{




    NotificationService getNotificationService();

}

