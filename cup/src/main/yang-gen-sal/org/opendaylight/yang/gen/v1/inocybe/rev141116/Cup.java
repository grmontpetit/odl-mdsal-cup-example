package org.opendaylight.yang.gen.v1.inocybe.rev141116;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.DisplayString;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.CupData;


/**
 * Top-level container for all toaster database objects.
 * <p>This class represents the following YANG schema fragment defined in module <b>cup</b>
 * <br />(Source path: <i>META-INF/yang/cup.yang</i>):
 * <pre>
 * container cup {
 *     leaf cupManufacturer {
 *         type DisplayString;
 *     }
 *     leaf cupModelNumber {
 *         type DisplayString;
 *     }
 *     leaf cupStatus {
 *         type enumeration;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>cup/cup</i>
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.inocybe.rev141116.CupBuilder}.
 * @see org.opendaylight.yang.gen.v1.inocybe.rev141116.CupBuilder
 */
public interface Cup
    extends
    ChildOf<CupData>,
    Augmentable<org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup>
{


    /**
     * The enumeration built-in type represents values from a set of assigned names.
     */
    public enum CupStatus {
        /**
         * The water in the cup is cold.
         */
        Cold(1),
        
        /**
         * The water is being heated in the microwave.
         */
        Heating(2)
        ;
    
    
        int value;
        static java.util.Map<java.lang.Integer, CupStatus> valueMap;
    
        static {
            valueMap = new java.util.HashMap<>();
            for (CupStatus enumItem : CupStatus.values())
            {
                valueMap.put(enumItem.value, enumItem);
            }
        }
    
        private CupStatus(int value) {
            this.value = value;
        }
        
        /**
         * @return integer value
         */
        public int getIntValue() {
            return value;
        }
    
        /**
         * @param valueArg
         * @return corresponding CupStatus item
         */
        public static CupStatus forValue(int valueArg) {
            return valueMap.get(valueArg);
        }
    }

    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("inocybe","2014-11-16","cup");;

    /**
     * The name of the cup's manufacturer. For instance China.
     */
    DisplayString getCupManufacturer();
    
    /**
     * The name of the cup's model. For instance, yellow cup.
     */
    DisplayString getCupModelNumber();
    
    /**
     * This variable indicates the current status of the cup.
     */
    CupStatus getCupStatus();

}

