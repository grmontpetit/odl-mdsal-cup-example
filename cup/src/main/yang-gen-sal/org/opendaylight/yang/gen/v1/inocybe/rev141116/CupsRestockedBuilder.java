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
 * Class that builds {@link org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked} instances.
 * @see org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked
 */
public class CupsRestockedBuilder {

    private java.lang.Long _amountOfCups;
    private static List<Range<BigInteger>> _amountOfCups_range;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked>> augmentation = new HashMap<>();

    public CupsRestockedBuilder() {
    } 

    public CupsRestockedBuilder(CupsRestocked base) {
        this._amountOfCups = base.getAmountOfCups();
        if (base instanceof CupsRestockedImpl) {
            CupsRestockedImpl _impl = (CupsRestockedImpl) base;
            this.augmentation = new HashMap<>(_impl.augmentation);
        }
    }


    public java.lang.Long getAmountOfCups() {
        return _amountOfCups;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

    public CupsRestockedBuilder setAmountOfCups(java.lang.Long value) {
        if (value != null) {
            BigInteger _constraint = BigInteger.valueOf(value);
            boolean isValidRange = false;
            for (Range<BigInteger> r : _amountOfCups_range()) {
                if (r.contains(_constraint)) {
                    isValidRange = true;
                }
            }
            if (!isValidRange) {
                throw new IllegalArgumentException(String.format("Invalid range: %s, expected: %s.", value, _amountOfCups_range));
            }
        }
        this._amountOfCups = value;
        return this;
    }
    public static List<Range<BigInteger>> _amountOfCups_range() {
        if (_amountOfCups_range == null) {
            synchronized (CupsRestockedBuilder.class) {
                if (_amountOfCups_range == null) {
                    ImmutableList.Builder<Range<BigInteger>> builder = ImmutableList.builder();
                    builder.add(Range.closed(BigInteger.ZERO, BigInteger.valueOf(4294967295L)));
                    _amountOfCups_range = builder.build();
                }
            }
        }
        return _amountOfCups_range;
    }
    
    public CupsRestockedBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked> augmentation) {
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }

    public CupsRestocked build() {
        return new CupsRestockedImpl(this);
    }

    private static final class CupsRestockedImpl implements CupsRestocked {

        public java.lang.Class<org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked.class;
        }

        private final java.lang.Long _amountOfCups;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked>> augmentation = new HashMap<>();

        private CupsRestockedImpl(CupsRestockedBuilder base) {
            this._amountOfCups = base.getAmountOfCups();
                switch (base.augmentation.size()) {
                case 0:
                    this.augmentation = Collections.emptyMap();
                    break;
                    case 1:
                        final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked>> e = base.augmentation.entrySet().iterator().next();
                        this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked>>singletonMap(e.getKey(), e.getValue());       
                    break;
                default :
                    this.augmentation = new HashMap<>(base.augmentation);
                }
        }

        @Override
        public java.lang.Long getAmountOfCups() {
            return _amountOfCups;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked>> E getAugmentation(java.lang.Class<E> augmentationType) {
            if (augmentationType == null) {
                throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
            }
            return (E) augmentation.get(augmentationType);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((_amountOfCups == null) ? 0 : _amountOfCups.hashCode());
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
            if (!org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked other = (org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked)obj;
            if (_amountOfCups == null) {
                if (other.getAmountOfCups() != null) {
                    return false;
                }
            } else if(!_amountOfCups.equals(other.getAmountOfCups())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                CupsRestockedImpl otherImpl = (CupsRestockedImpl) obj;
                if (augmentation == null) {
                    if (otherImpl.augmentation != null) {
                        return false;
                    }
                } else if(!augmentation.equals(otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.CupsRestocked>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("CupsRestocked [");
            boolean first = true;
        
            if (_amountOfCups != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_amountOfCups=");
                builder.append(_amountOfCups);
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
