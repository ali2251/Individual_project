package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import java.util.Objects;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Map;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket} instances.
 *
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket
 *
 */
public class LatencyPacketBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket> {

    private BigInteger _latency;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket>> augmentation = Collections.emptyMap();

    public LatencyPacketBuilder() {
    }

    public LatencyPacketBuilder(LatencyPacket base) {
        this._latency = base.getLatency();
        if (base instanceof LatencyPacketImpl) {
            LatencyPacketImpl impl = (LatencyPacketImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public BigInteger getLatency() {
        return _latency;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

     
     private static final com.google.common.collect.Range<java.math.BigInteger>[] CHECKLATENCYRANGE_RANGES;
     static {
         @SuppressWarnings("unchecked")
         final com.google.common.collect.Range<java.math.BigInteger>[] a = (com.google.common.collect.Range<java.math.BigInteger>[]) java.lang.reflect.Array.newInstance(com.google.common.collect.Range.class, 1);
         a[0] = com.google.common.collect.Range.closed(java.math.BigInteger.ZERO, new java.math.BigInteger("18446744073709551615"));
         CHECKLATENCYRANGE_RANGES = a;
     }
     private static void checkLatencyRange(final java.math.BigInteger value) {
         for (com.google.common.collect.Range<java.math.BigInteger> r : CHECKLATENCYRANGE_RANGES) {
             if (r.contains(value)) {
                 return;
             }
         }
         throw new IllegalArgumentException(String.format("Invalid range: %s, expected: %s.", value, java.util.Arrays.asList(CHECKLATENCYRANGE_RANGES)));
     }
    
    public LatencyPacketBuilder setLatency(final BigInteger value) {
    if (value != null) {
        checkLatencyRange(value);
    }
        this._latency = value;
        return this;
    }
    
    public LatencyPacketBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public LatencyPacketBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public LatencyPacket build() {
        return new LatencyPacketImpl(this);
    }

    private static final class LatencyPacketImpl implements LatencyPacket {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket.class;
        }

        private final BigInteger _latency;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket>> augmentation = Collections.emptyMap();

        private LatencyPacketImpl(LatencyPacketBuilder base) {
            this._latency = base.getLatency();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public BigInteger getLatency() {
            return _latency;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket>> E getAugmentation(java.lang.Class<E> augmentationType) {
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
            result = prime * result + Objects.hashCode(_latency);
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
            if (!org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket other = (org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket)obj;
            if (!Objects.equals(_latency, other.getLatency())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                LatencyPacketImpl otherImpl = (LatencyPacketImpl) obj;
                if (!Objects.equals(augmentation, otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("LatencyPacket [");
            boolean first = true;
        
            if (_latency != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_latency=");
                builder.append(_latency);
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
