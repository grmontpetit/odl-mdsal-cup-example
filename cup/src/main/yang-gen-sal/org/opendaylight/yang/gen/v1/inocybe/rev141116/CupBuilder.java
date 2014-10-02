package org.opendaylight.yang.gen.v1.inocybe.rev141116;
import com.google.common.collect.Range;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup.CupStatus;
import java.util.Collections;
import java.util.Map;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import com.google.common.collect.ImmutableList;
import java.math.BigInteger;
import java.util.List;
import org.opendaylight.yang.gen.v1.inocybe.rev141116.DisplayString;
import org.opendaylight.yangtools.yang.binding.Augmentation;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup} instances.
 * @see org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup
 */
public class CupBuilder {

    private DisplayString _cupManufacturer;
    private static List<Range<BigInteger>> _cupManufacturer_length;
    private DisplayString _cupModelNumber;
    private static List<Range<BigInteger>> _cupModelNumber_length;
    private CupStatus _cupStatus;
    private java.lang.Long _cupTemperatureFactor;
    private static List<Range<BigInteger>> _cupTemperatureFactor_range;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup>> augmentation = new HashMap<>();

    public CupBuilder() {
    } 

    public CupBuilder(Cup base) {
        this._cupManufacturer = base.getCupManufacturer();
        this._cupModelNumber = base.getCupModelNumber();
        this._cupStatus = base.getCupStatus();
        this._cupTemperatureFactor = base.getCupTemperatureFactor();
        if (base instanceof CupImpl) {
            CupImpl _impl = (CupImpl) base;
            this.augmentation = new HashMap<>(_impl.augmentation);
        }
    }


    public DisplayString getCupManufacturer() {
        return _cupManufacturer;
    }
    
    public DisplayString getCupModelNumber() {
        return _cupModelNumber;
    }
    
    public CupStatus getCupStatus() {
        return _cupStatus;
    }
    
    public java.lang.Long getCupTemperatureFactor() {
        return _cupTemperatureFactor;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

    public CupBuilder setCupManufacturer(DisplayString value) {
        if (value != null) {
            BigInteger _constraint = BigInteger.valueOf(value.getValue().length());
            boolean isValidLength = false;
            for (Range<BigInteger> r : _cupManufacturer_length()) {
                if (r.contains(_constraint)) {
                    isValidLength = true;
                }
            }
            if (!isValidLength) {
                throw new IllegalArgumentException(String.format("Invalid length: %s, expected: %s.", value, _cupManufacturer_length));
            }
        }
        this._cupManufacturer = value;
        return this;
    }
    public static List<Range<BigInteger>> _cupManufacturer_length() {
        if (_cupManufacturer_length == null) {
            synchronized (CupBuilder.class) {
                if (_cupManufacturer_length == null) {
                    ImmutableList.Builder<Range<BigInteger>> builder = ImmutableList.builder();
                    builder.add(Range.closed(BigInteger.ZERO, BigInteger.valueOf(255L)));
                    _cupManufacturer_length = builder.build();
                }
            }
        }
        return _cupManufacturer_length;
    }
    
    public CupBuilder setCupModelNumber(DisplayString value) {
        if (value != null) {
            BigInteger _constraint = BigInteger.valueOf(value.getValue().length());
            boolean isValidLength = false;
            for (Range<BigInteger> r : _cupModelNumber_length()) {
                if (r.contains(_constraint)) {
                    isValidLength = true;
                }
            }
            if (!isValidLength) {
                throw new IllegalArgumentException(String.format("Invalid length: %s, expected: %s.", value, _cupModelNumber_length));
            }
        }
        this._cupModelNumber = value;
        return this;
    }
    public static List<Range<BigInteger>> _cupModelNumber_length() {
        if (_cupModelNumber_length == null) {
            synchronized (CupBuilder.class) {
                if (_cupModelNumber_length == null) {
                    ImmutableList.Builder<Range<BigInteger>> builder = ImmutableList.builder();
                    builder.add(Range.closed(BigInteger.ZERO, BigInteger.valueOf(255L)));
                    _cupModelNumber_length = builder.build();
                }
            }
        }
        return _cupModelNumber_length;
    }
    
    public CupBuilder setCupStatus(CupStatus value) {
        this._cupStatus = value;
        return this;
    }
    
    public CupBuilder setCupTemperatureFactor(java.lang.Long value) {
        if (value != null) {
            BigInteger _constraint = BigInteger.valueOf(value);
            boolean isValidRange = false;
            for (Range<BigInteger> r : _cupTemperatureFactor_range()) {
                if (r.contains(_constraint)) {
                    isValidRange = true;
                }
            }
            if (!isValidRange) {
                throw new IllegalArgumentException(String.format("Invalid range: %s, expected: %s.", value, _cupTemperatureFactor_range));
            }
        }
        this._cupTemperatureFactor = value;
        return this;
    }
    public static List<Range<BigInteger>> _cupTemperatureFactor_range() {
        if (_cupTemperatureFactor_range == null) {
            synchronized (CupBuilder.class) {
                if (_cupTemperatureFactor_range == null) {
                    ImmutableList.Builder<Range<BigInteger>> builder = ImmutableList.builder();
                    builder.add(Range.closed(BigInteger.ZERO, BigInteger.valueOf(4294967295L)));
                    _cupTemperatureFactor_range = builder.build();
                }
            }
        }
        return _cupTemperatureFactor_range;
    }
    
    public CupBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup> augmentation) {
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }

    public Cup build() {
        return new CupImpl(this);
    }

    private static final class CupImpl implements Cup {

        public java.lang.Class<org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup.class;
        }

        private final DisplayString _cupManufacturer;
        private final DisplayString _cupModelNumber;
        private final CupStatus _cupStatus;
        private final java.lang.Long _cupTemperatureFactor;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup>> augmentation = new HashMap<>();

        private CupImpl(CupBuilder base) {
            this._cupManufacturer = base.getCupManufacturer();
            this._cupModelNumber = base.getCupModelNumber();
            this._cupStatus = base.getCupStatus();
            this._cupTemperatureFactor = base.getCupTemperatureFactor();
                switch (base.augmentation.size()) {
                case 0:
                    this.augmentation = Collections.emptyMap();
                    break;
                    case 1:
                        final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup>> e = base.augmentation.entrySet().iterator().next();
                        this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup>>singletonMap(e.getKey(), e.getValue());       
                    break;
                default :
                    this.augmentation = new HashMap<>(base.augmentation);
                }
        }

        @Override
        public DisplayString getCupManufacturer() {
            return _cupManufacturer;
        }
        
        @Override
        public DisplayString getCupModelNumber() {
            return _cupModelNumber;
        }
        
        @Override
        public CupStatus getCupStatus() {
            return _cupStatus;
        }
        
        @Override
        public java.lang.Long getCupTemperatureFactor() {
            return _cupTemperatureFactor;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup>> E getAugmentation(java.lang.Class<E> augmentationType) {
            if (augmentationType == null) {
                throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
            }
            return (E) augmentation.get(augmentationType);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((_cupManufacturer == null) ? 0 : _cupManufacturer.hashCode());
            result = prime * result + ((_cupModelNumber == null) ? 0 : _cupModelNumber.hashCode());
            result = prime * result + ((_cupStatus == null) ? 0 : _cupStatus.hashCode());
            result = prime * result + ((_cupTemperatureFactor == null) ? 0 : _cupTemperatureFactor.hashCode());
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
            if (!org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup other = (org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup)obj;
            if (_cupManufacturer == null) {
                if (other.getCupManufacturer() != null) {
                    return false;
                }
            } else if(!_cupManufacturer.equals(other.getCupManufacturer())) {
                return false;
            }
            if (_cupModelNumber == null) {
                if (other.getCupModelNumber() != null) {
                    return false;
                }
            } else if(!_cupModelNumber.equals(other.getCupModelNumber())) {
                return false;
            }
            if (_cupStatus == null) {
                if (other.getCupStatus() != null) {
                    return false;
                }
            } else if(!_cupStatus.equals(other.getCupStatus())) {
                return false;
            }
            if (_cupTemperatureFactor == null) {
                if (other.getCupTemperatureFactor() != null) {
                    return false;
                }
            } else if(!_cupTemperatureFactor.equals(other.getCupTemperatureFactor())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                CupImpl otherImpl = (CupImpl) obj;
                if (augmentation == null) {
                    if (otherImpl.augmentation != null) {
                        return false;
                    }
                } else if(!augmentation.equals(otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup>>, Augmentation<org.opendaylight.yang.gen.v1.inocybe.rev141116.Cup>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("Cup [");
            boolean first = true;
        
            if (_cupManufacturer != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_cupManufacturer=");
                builder.append(_cupManufacturer);
             }
            if (_cupModelNumber != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_cupModelNumber=");
                builder.append(_cupModelNumber);
             }
            if (_cupStatus != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_cupStatus=");
                builder.append(_cupStatus);
             }
            if (_cupTemperatureFactor != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_cupTemperatureFactor=");
                builder.append(_cupTemperatureFactor);
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
