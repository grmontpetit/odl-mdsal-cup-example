package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state;
import java.util.Collections;
import java.util.Map;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import org.opendaylight.yangtools.yang.binding.Augmentation;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl} instances.
 * @see org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl
 */
public class ButlerServiceImplBuilder {


    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl>> augmentation = new HashMap<>();

    public ButlerServiceImplBuilder() {
    } 
    

    public ButlerServiceImplBuilder(ButlerServiceImpl base) {
        if (base instanceof ButlerServiceImplImpl) {
            ButlerServiceImplImpl _impl = (ButlerServiceImplImpl) base;
            this.augmentation = new HashMap<>(_impl.augmentation);
        }
    }


    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

    
    public ButlerServiceImplBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl> augmentation) {
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }

    public ButlerServiceImpl build() {
        return new ButlerServiceImplImpl(this);
    }

    private static final class ButlerServiceImplImpl implements ButlerServiceImpl {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl.class;
        }


        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl>> augmentation = new HashMap<>();

        private ButlerServiceImplImpl(ButlerServiceImplBuilder base) {
                switch (base.augmentation.size()) {
                case 0:
                    this.augmentation = Collections.emptyMap();
                    break;
                    case 1:
                        final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl>> e = base.augmentation.entrySet().iterator().next();
                        this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl>>singletonMap(e.getKey(), e.getValue());       
                    break;
                default :
                    this.augmentation = new HashMap<>(base.augmentation);
                }
        }

        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl>> E getAugmentation(java.lang.Class<E> augmentationType) {
            if (augmentationType == null) {
                throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
            }
            return (E) augmentation.get(augmentationType);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
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
            if (!org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl other = (org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl)obj;
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                ButlerServiceImplImpl otherImpl = (ButlerServiceImplImpl) obj;
                if (augmentation == null) {
                    if (otherImpl.augmentation != null) {
                        return false;
                    }
                } else if(!augmentation.equals(otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.butler.service.impl.rev141116.modules.module.state.ButlerServiceImpl>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("ButlerServiceImpl [");
            boolean first = true;
        
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
