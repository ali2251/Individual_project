package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import java.math.BigInteger;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.Notification;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>monitoring</b>
 * <br>(Source path: <i>META-INF/yang/monitoring.yang</i>):
 * <pre>
 * notification latencyPacket {
 *     leaf latency {
 *         type uint64;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>monitoring/latencyPacket</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacketBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacketBuilder
 *
 */
public interface LatencyPacket
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.LatencyPacket>,
    Notification
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:monitoring",
        "2015-07-22", "latencyPacket").intern();

    BigInteger getLatency();

}

