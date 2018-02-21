package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import java.util.Objects;
import java.util.Collections;
import java.util.Map;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput} instances.
 *
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput
 *
 */
public class InstallFlowOutputBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput> {

    private java.lang.String _success;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput>> augmentation = Collections.emptyMap();

    public InstallFlowOutputBuilder() {
    }

    public InstallFlowOutputBuilder(InstallFlowOutput base) {
        this._success = base.getSuccess();
        if (base instanceof InstallFlowOutputImpl) {
            InstallFlowOutputImpl impl = (InstallFlowOutputImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public java.lang.String getSuccess() {
        return _success;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

     
    public InstallFlowOutputBuilder setSuccess(final java.lang.String value) {
        this._success = value;
        return this;
    }
    
    public InstallFlowOutputBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public InstallFlowOutputBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public InstallFlowOutput build() {
        return new InstallFlowOutputImpl(this);
    }

    private static final class InstallFlowOutputImpl implements InstallFlowOutput {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput.class;
        }

        private final java.lang.String _success;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput>> augmentation = Collections.emptyMap();

        private InstallFlowOutputImpl(InstallFlowOutputBuilder base) {
            this._success = base.getSuccess();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public java.lang.String getSuccess() {
            return _success;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput>> E getAugmentation(java.lang.Class<E> augmentationType) {
            if (augmentationType == null) {
                throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
            }
            return (E) augmentation.get(augmentationType);
        }

        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int prime = 31;
            int result = 1;
            result = prime * result + Objects.hashCode(_success);
            result = prime * result + Objects.hashCode(augmentation);
        
            hash = result;
            hashValid = true;
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
            if (!org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput other = (org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput)obj;
            if (!Objects.equals(_success, other.getSuccess())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                InstallFlowOutputImpl otherImpl = (InstallFlowOutputImpl) obj;
                if (!Objects.equals(augmentation, otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.InstallFlowOutput>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("InstallFlowOutput [");
            boolean first = true;
        
            if (_success != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_success=");
                builder.append(_success);
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
