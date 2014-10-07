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
 * Class that builds {@link org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput} instances.
 * @see org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput
 */
public class RestockCupsInputBuilder {

    private java.lang.Long _amountOfCupsToClean;
    private static List<Range<BigInteger>> _amountOfCupsToClean_range;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput>> augmentation = new HashMap<>();

    public RestockCupsInputBuilder() {
    } 

    public RestockCupsInputBuilder(RestockCupsInput base) {
        this._amountOfCupsToClean = base.getAmountOfCupsToClean();
        if (base instanceof RestockCupsInputImpl) {
            RestockCupsInputImpl _impl = (RestockCupsInputImpl) base;
            this.augmentation = new HashMap<>(_impl.augmentation);
        }
    }


    public java.lang.Long getAmountOfCupsToClean() {
        return _amountOfCupsToClean;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

    public RestockCupsInputBuilder setAmountOfCupsToClean(java.lang.Long value) {
        if (value != null) {
            BigInteger _constraint = BigInteger.valueOf(value);
            boolean isValidRange = false;
            for (Range<BigInteger> r : _amountOfCupsToClean_range()) {
                if (r.contains(_constraint)) {
                    isValidRange = true;
                }
            }
            if (!isValidRange) {
                throw new IllegalArgumentException(String.format("Invalid range: %s, expected: %s.", value, _amountOfCupsToClean_range));
            }
        }
        this._amountOfCupsToClean = value;
        return this;
    }
    public static List<Range<BigInteger>> _amountOfCupsToClean_range() {
        if (_amountOfCupsToClean_range == null) {
            synchronized (RestockCupsInputBuilder.class) {
                if (_amountOfCupsToClean_range == null) {
                    ImmutableList.Builder<Range<BigInteger>> builder = ImmutableList.builder();
                    builder.add(Range.closed(BigInteger.ZERO, BigInteger.valueOf(4294967295L)));
                    _amountOfCupsToClean_range = builder.build();
                }
            }
        }
        return _amountOfCupsToClean_range;
    }
    
    public RestockCupsInputBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput> augmentation) {
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }

    public RestockCupsInput build() {
        return new RestockCupsInputImpl(this);
    }

    private static final class RestockCupsInputImpl implements RestockCupsInput {

        public java.lang.Class<org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput.class;
        }

        private final java.lang.Long _amountOfCupsToClean;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput>> augmentation = new HashMap<>();

        private RestockCupsInputImpl(RestockCupsInputBuilder base) {
            this._amountOfCupsToClean = base.getAmountOfCupsToClean();
                switch (base.augmentation.size()) {
                case 0:
                    this.augmentation = Collections.emptyMap();
                    break;
                    case 1:
                        final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput>> e = base.augmentation.entrySet().iterator().next();
                        this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput>>singletonMap(e.getKey(), e.getValue());       
                    break;
                default :
                    this.augmentation = new HashMap<>(base.augmentation);
                }
        }

        @Override
        public java.lang.Long getAmountOfCupsToClean() {
            return _amountOfCupsToClean;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput>> E getAugmentation(java.lang.Class<E> augmentationType) {
            if (augmentationType == null) {
                throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
            }
            return (E) augmentation.get(augmentationType);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((_amountOfCupsToClean == null) ? 0 : _amountOfCupsToClean.hashCode());
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
            if (!org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput other = (org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput)obj;
            if (_amountOfCupsToClean == null) {
                if (other.getAmountOfCupsToClean() != null) {
                    return false;
                }
            } else if(!_amountOfCupsToClean.equals(other.getAmountOfCupsToClean())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                RestockCupsInputImpl otherImpl = (RestockCupsInputImpl) obj;
                if (augmentation == null) {
                    if (otherImpl.augmentation != null) {
                        return false;
                    }
                } else if(!augmentation.equals(otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.RestockCupsInput>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("RestockCupsInput [");
            boolean first = true;
        
            if (_amountOfCupsToClean != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_amountOfCupsToClean=");
                builder.append(_amountOfCupsToClean);
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
