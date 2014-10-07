package org.opendaylight.yang.gen.v1.inocybe.rev141116;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>cup</b>
 * <br />(Source path: <i>META-INF/yang/cup.yang</i>):
 * <pre>
 * container input {
 *     leaf amountOfCupsToClean {
 *         type uint32;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>cup/restock-cups/input</i>
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInputBuilder}.
 * @see org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInputBuilder
 */
public interface RestockCupsInput
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("inocybe","2014-11-16","input");;

    /**
     * Indicates the amount of cups to clean.
     */
    java.lang.Long getAmountOfCupsToClean();

}

