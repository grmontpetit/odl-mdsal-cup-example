package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116;
import java.util.Collections;
import java.util.Map;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import org.opendaylight.yangtools.yang.binding.Augmentation;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput} instances.
 * @see org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput
 */
public class MakeBlackTeaWithLapresseOutputBuilder {

    private java.lang.Boolean _result;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput>> augmentation = new HashMap<>();

    public MakeBlackTeaWithLapresseOutputBuilder() {
    } 

    public MakeBlackTeaWithLapresseOutputBuilder(MakeBlackTeaWithLapresseOutput base) {
        this._result = base.isResult();
        if (base instanceof MakeBlackTeaWithLapresseOutputImpl) {
            MakeBlackTeaWithLapresseOutputImpl _impl = (MakeBlackTeaWithLapresseOutputImpl) base;
            this.augmentation = new HashMap<>(_impl.augmentation);
        }
    }


    public java.lang.Boolean isResult() {
        return _result;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

    public MakeBlackTeaWithLapresseOutputBuilder setResult(java.lang.Boolean value) {
        this._result = value;
        return this;
    }
    
    public MakeBlackTeaWithLapresseOutputBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput> augmentation) {
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }

    public MakeBlackTeaWithLapresseOutput build() {
        return new MakeBlackTeaWithLapresseOutputImpl(this);
    }

    private static final class MakeBlackTeaWithLapresseOutputImpl implements MakeBlackTeaWithLapresseOutput {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput.class;
        }

        private final java.lang.Boolean _result;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput>> augmentation = new HashMap<>();

        private MakeBlackTeaWithLapresseOutputImpl(MakeBlackTeaWithLapresseOutputBuilder base) {
            this._result = base.isResult();
                switch (base.augmentation.size()) {
                case 0:
                    this.augmentation = Collections.emptyMap();
                    break;
                    case 1:
                        final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput>> e = base.augmentation.entrySet().iterator().next();
                        this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput>>singletonMap(e.getKey(), e.getValue());       
                    break;
                default :
                    this.augmentation = new HashMap<>(base.augmentation);
                }
        }

        @Override
        public java.lang.Boolean isResult() {
            return _result;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput>> E getAugmentation(java.lang.Class<E> augmentationType) {
            if (augmentationType == null) {
                throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
            }
            return (E) augmentation.get(augmentationType);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((_result == null) ? 0 : _result.hashCode());
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
            if (!org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput other = (org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput)obj;
            if (_result == null) {
                if (other.isResult() != null) {
                    return false;
                }
            } else if(!_result.equals(other.isResult())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                MakeBlackTeaWithLapresseOutputImpl otherImpl = (MakeBlackTeaWithLapresseOutputImpl) obj;
                if (augmentation == null) {
                    if (otherImpl.augmentation != null) {
                        return false;
                    }
                } else if(!augmentation.equals(otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.MakeBlackTeaWithLapresseOutput>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("MakeBlackTeaWithLapresseOutput [");
            boolean first = true;
        
            if (_result != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_result=");
                builder.append(_result);
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
