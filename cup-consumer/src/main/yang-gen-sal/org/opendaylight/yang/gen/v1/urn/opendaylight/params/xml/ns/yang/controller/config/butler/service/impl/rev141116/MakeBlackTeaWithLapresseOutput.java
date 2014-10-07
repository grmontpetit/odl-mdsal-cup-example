package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>butler-service-impl</b>
 * <br />(Source path: <i>META-INF/yang/butler-service-impl.yang</i>):
 * <pre>
 * container output {
 *     leaf result {
 *         type boolean;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>butler-service-impl/make-black-tea-with-lapresse/output</i>
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutputBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutputBuilder
 */
public interface MakeBlackTeaWithLapresseOutput
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:opendaylight:params:xml:ns:yang:controller:config:butler-service:impl","2014-11-16","output");;

    java.lang.Boolean isResult();

}

