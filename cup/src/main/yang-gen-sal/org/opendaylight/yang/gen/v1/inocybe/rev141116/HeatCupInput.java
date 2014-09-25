package org.opendaylight.yang.gen.v1.inocybe.rev141116;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>cup</b>
 * <br />(Source path: <i>META-INF/yang/cup.yang</i>):
 * <pre>
 * container input {
 *     leaf cupTemperature {
 *         type uint32;
 *     }
 *     leaf cupTeaType {
 *         type identityref;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>cup/heat-cup/input</i>
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInputBuilder}.
 * @see org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInputBuilder
 */
public interface HeatCupInput
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("inocybe","2014-11-16","input");;

    /**
     * This variable controls the heat of the water. It should be on a scale of 75 to 
     * 100. Water temperature of 100 is better for black teas and red teas while 
     * temperatures of 75 are better for green teas.
     */
    java.lang.Long getCupTemperature();
    
    /**
     * This variable informs the cup which tea is beign used. The cup uses this 
     * information, combined with cupTemperature, to compute for how long the material 
     * must be heated to achieve the required temperature.
     */
    java.lang.Class<? extends org.opendaylight.yang.gen.v1.inocybe.rev141116.TeaType> getCupTeaType();

}

