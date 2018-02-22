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
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput} instances.
 *
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput
 *
 */
public class DeleteFlowOutputBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput> {

    private java.lang.String _success;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput>> augmentation = Collections.emptyMap();

    public DeleteFlowOutputBuilder() {
    }

    public DeleteFlowOutputBuilder(DeleteFlowOutput base) {
        this._success = base.getSuccess();
        if (base instanceof DeleteFlowOutputImpl) {
            DeleteFlowOutputImpl impl = (DeleteFlowOutputImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public java.lang.String getSuccess() {
        return _success;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

     
    public DeleteFlowOutputBuilder setSuccess(final java.lang.String value) {
        this._success = value;
        return this;
    }
    
    public DeleteFlowOutputBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public DeleteFlowOutputBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public DeleteFlowOutput build() {
        return new DeleteFlowOutputImpl(this);
    }

    private static final class DeleteFlowOutputImpl implements DeleteFlowOutput {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput.class;
        }

        private final java.lang.String _success;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput>> augmentation = Collections.emptyMap();

        private DeleteFlowOutputImpl(DeleteFlowOutputBuilder base) {
            this._success = base.getSuccess();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput>>singletonMap(e.getKey(), e.getValue());
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
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput>> E getAugmentation(java.lang.Class<E> augmentationType) {
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
            if (!org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput other = (org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput)obj;
            if (!Objects.equals(_success, other.getSuccess())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                DeleteFlowOutputImpl otherImpl = (DeleteFlowOutputImpl) obj;
                if (!Objects.equals(augmentation, otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.DeleteFlowOutput>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("DeleteFlowOutput [");
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
