package org.opendaylight.yang.gen.v1.http.netconfcentral.org.ns.cup.rev691231;
import com.google.common.collect.Range;
import java.io.Serializable;
import com.google.common.collect.ImmutableList;
import java.math.BigInteger;
import java.util.List;
import java.beans.ConstructorProperties;


/**
 * YANG version of the SMIv2 DisplayString TEXTUAL-CONVENTION.
 */
public class DisplayString
 implements Serializable {
    private static final long serialVersionUID = -5323910593144881832L; 
    private static final List<Range<BigInteger>> _length;
    final private java.lang.String _value;
    
    static {
        ImmutableList.Builder<Range<BigInteger>> builder = ImmutableList.builder();
        builder.add(Range.closed(BigInteger.ZERO, BigInteger.valueOf(255L)));
        _length = builder.build();
    }
    
    @ConstructorProperties("value")
    public DisplayString(java.lang.String _value) {
        if (_value != null) {
            BigInteger _constraint = BigInteger.valueOf(_value.length());
            boolean isValidLength = false;
            for (Range<BigInteger> r : length()) {
                if (r.contains(_constraint)) {
                    isValidLength = true;
                }
            }
            if (!isValidLength) {
                throw new IllegalArgumentException(String.format("Invalid length: %s, expected: %s.", _value, length()));
            }
        }
        this._value = _value;
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public DisplayString(DisplayString source) {
        this._value = source._value;
    }

    public static DisplayString getDefaultInstance(String defaultValue) {
        return new DisplayString(defaultValue);
    }

    public java.lang.String getValue() {
        return _value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_value == null) ? 0 : _value.hashCode());
        return result;
    }

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DisplayString other = (DisplayString) obj;
        if (_value == null) {
            if (other._value != null) {
                return false;
            }
        } else if(!_value.equals(other._value)) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toString() {
        java.lang.StringBuilder builder = new java.lang.StringBuilder(org.opendaylight.yang.gen.v1.http.netconfcentral.org.ns.cup.rev691231.DisplayString.class.getSimpleName()).append(" [");
        boolean first = true;
    
        if (_value != null) {
            if (first) {
                first = false;
            } else {
                builder.append(", ");
            }
            builder.append("_value=");
            builder.append(_value);
         }
        return builder.append(']').toString();
    }

    public static List<Range<BigInteger>> length() {
        return _length;
    }


}
