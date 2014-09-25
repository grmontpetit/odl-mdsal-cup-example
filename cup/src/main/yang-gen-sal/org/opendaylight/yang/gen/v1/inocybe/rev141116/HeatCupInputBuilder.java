package org.opendaylight.yang.gen.v1.inocybe.rev141116;
import com.google.common.collect.Range;
import java.util.Collections;
import java.util.Map;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import com.google.common.collect.ImmutableList;
import java.math.BigInteger;
import java.util.List;
import org.opendaylight.yangtools.yang.binding.Augmentation;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput} instances.
 * @see org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput
 */
public class HeatCupInputBuilder {

    private java.lang.Class<? extends org.opendaylight.yang.gen.v1.inocybe.rev141116.TeaType> _cupTeaType;
    private java.lang.Long _cupTemperature;
    private static List<Range<BigInteger>> _cupTemperature_range;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput>> augmentation = new HashMap<>();

    public HeatCupInputBuilder() {
    } 

    public HeatCupInputBuilder(HeatCupInput base) {
        this._cupTeaType = base.getCupTeaType();
        this._cupTemperature = base.getCupTemperature();
        if (base instanceof HeatCupInputImpl) {
            HeatCupInputImpl _impl = (HeatCupInputImpl) base;
            this.augmentation = new HashMap<>(_impl.augmentation);
        }
    }


    public java.lang.Class<? extends org.opendaylight.yang.gen.v1.inocybe.rev141116.TeaType> getCupTeaType() {
        return _cupTeaType;
    }
    
    public java.lang.Long getCupTemperature() {
        return _cupTemperature;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

    public HeatCupInputBuilder setCupTeaType(java.lang.Class<? extends org.opendaylight.yang.gen.v1.inocybe.rev141116.TeaType> value) {
        this._cupTeaType = value;
        return this;
    }
    
    public HeatCupInputBuilder setCupTemperature(java.lang.Long value) {
        if (value != null) {
            BigInteger _constraint = BigInteger.valueOf(value);
            boolean isValidRange = false;
            for (Range<BigInteger> r : _cupTemperature_range()) {
                if (r.contains(_constraint)) {
                    isValidRange = true;
                }
            }
            if (!isValidRange) {
                throw new IllegalArgumentException(String.format("Invalid range: %s, expected: %s.", value, _cupTemperature_range));
            }
        }
        this._cupTemperature = value;
        return this;
    }
    public static List<Range<BigInteger>> _cupTemperature_range() {
        if (_cupTemperature_range == null) {
            synchronized (HeatCupInputBuilder.class) {
                if (_cupTemperature_range == null) {
                    ImmutableList.Builder<Range<BigInteger>> builder = ImmutableList.builder();
                    builder.add(Range.closed(BigInteger.valueOf(75L), BigInteger.valueOf(100L)));
                    _cupTemperature_range = builder.build();
                }
            }
        }
        return _cupTemperature_range;
    }
    
    public HeatCupInputBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput> augmentation) {
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }

    public HeatCupInput build() {
        return new HeatCupInputImpl(this);
    }

    private static final class HeatCupInputImpl implements HeatCupInput {

        public java.lang.Class<org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput.class;
        }

        private final java.lang.Class<? extends org.opendaylight.yang.gen.v1.inocybe.rev141116.TeaType> _cupTeaType;
        private final java.lang.Long _cupTemperature;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput>> augmentation = new HashMap<>();

        private HeatCupInputImpl(HeatCupInputBuilder base) {
            this._cupTeaType = base.getCupTeaType();
            this._cupTemperature = base.getCupTemperature();
                switch (base.augmentation.size()) {
                case 0:
                    this.augmentation = Collections.emptyMap();
                    break;
                    case 1:
                        final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput>> e = base.augmentation.entrySet().iterator().next();
                        this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput>>singletonMap(e.getKey(), e.getValue());       
                    break;
                default :
                    this.augmentation = new HashMap<>(base.augmentation);
                }
        }

        @Override
        public java.lang.Class<? extends org.opendaylight.yang.gen.v1.inocybe.rev141116.TeaType> getCupTeaType() {
            return _cupTeaType;
        }
        
        @Override
        public java.lang.Long getCupTemperature() {
            return _cupTemperature;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput>> E getAugmentation(java.lang.Class<E> augmentationType) {
            if (augmentationType == null) {
                throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
            }
            return (E) augmentation.get(augmentationType);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((_cupTeaType == null) ? 0 : _cupTeaType.hashCode());
            result = prime * result + ((_cupTemperature == null) ? 0 : _cupTemperature.hashCode());
            result = prime * result + ((augmentation == null) ? 0 : augmentation.hashCode());
            return result;
        }

        @Override
        public boolean equals(java.lang.Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DataObject)) {
                return false;
            }
            if (!org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput other = (org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput)obj;
            if (_cupTeaType == null) {
                if (other.getCupTeaType() != null) {
                    return false;
                }
            } else if(!_cupTeaType.equals(other.getCupTeaType())) {
                return false;
            }
            if (_cupTemperature == null) {
                if (other.getCupTemperature() != null) {
                    return false;
                }
            } else if(!_cupTemperature.equals(other.getCupTemperature())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                HeatCupInputImpl otherImpl = (HeatCupInputImpl) obj;
                if (augmentation == null) {
                    if (otherImpl.augmentation != null) {
                        return false;
                    }
                } else if(!augmentation.equals(otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.HeatCupInput>> e : augmentation.entrySet()) {
                    if (!e.getValue().equals(other.getAugmentation(e.getKey()))) {
                        return false;
                    }
                }
                // .. and give the other one the chance to do the same
                if (!obj.equals(this)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public java.lang.String toString() {
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("HeatCupInput [");
            boolean first = true;
        
            if (_cupTeaType != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_cupTeaType=");
                builder.append(_cupTeaType);
             }
            if (_cupTemperature != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_cupTemperature=");
                builder.append(_cupTemperature);
             }
            if (first) {
                first = false;
            } else {
                builder.append(", ");
            }
            builder.append("augmentation=");
            builder.append(augmentation.values());
            return builder.append(']').toString();
        }
    }

}
