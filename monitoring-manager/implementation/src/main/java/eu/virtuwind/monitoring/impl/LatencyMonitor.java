package eu.virtuwind.monitoring.impl;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.CheckedFuture;
import org.eclipse.xtend.lib.macro.services.SourceTypeLookup;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.ReadOnlyTransaction;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.controller.md.sal.common.api.data.ReadFailedException;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketProcessingService;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NetworkTopology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.TopologyId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.Topology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.TopologyKey;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Link;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LatencyMonitor implements MonitoringListener {

    private static DataBroker db;

    public static Long latency = -1L;

    private static HashMap<Link, Long> latencies = new HashMap<>();
    private static HashMap<Link, Long> jitters = new HashMap<>();
    private static ArrayList<Link> links = new ArrayList<>();
    private static PacketProcessingService packetProcessingService;
    private static PacketSender packetSender;

    public LatencyMonitor(DataBroker dataBroker, PacketSender packetSender1)  {
        db = dataBroker;
        packetSender = packetSender1;
    }
    public LatencyMonitor() {}


    public void onTopologyChanged(TopologyChanged notification) {

    }

    public void onLatencyPacket(LatencyPacket notification){

        System.out.println("Reched here");
        System.out.println(notification.getLatency());
    }


    public Long MeasureNextLink(Link link) {


        latency = -10000000L;
        String node_id = link.getSource().getSourceNode().getValue();
        String node_connector_id = link.getSource().getSourceTp().getValue();




        boolean success = packetSender.sendPacketToController(0, node_id, node_connector_id);

        System.out.println("success " + success);

        while (latency.equals(-10000000L)) {
            //waiting
            System.out.print("");

        }
        //latency is now not -1


        Long controllerLatency = latency;
        System.out.println("controller latency is " + controllerLatency);


        latency = -10000000L;
        packetSender.sendPacket(0, node_connector_id, node_id);

        while (latency.equals(-10000000L)) {
            //waiting
            System.out.print("");

        }

        System.out.println("overall latency is " + latency);


        Long latencyToreturn =  latency - controllerLatency;

        System.out.println("latency then is " + latencyToreturn);


        return latencyToreturn;


    }


    public Long MeasureNextLinkJitter(Link link) {

        latency = -10000000L;
        String node_id = link.getSource().getSourceNode().getValue();
        String node_connector_id = link.getSource().getSourceTp().getValue();


         packetSender.sendPacketToController(0, node_id, node_connector_id);


        while (latency.equals(-10000000L)) {
            //waiting
            System.out.print("");

        }
        //latency is now not -1


        Long controllerLatency = latency;

        latency = -10000000L;
        packetSender.sendPacket(0, node_connector_id, node_id);
        while (latency.equals(-10000000L)) {
            //waiting
            System.out.print("");

        }
        //latency is now not -1
        Long latency1 = latency - controllerLatency;

        latency = -10000000L;


        packetSender.sendPacketToController(0, node_id, node_connector_id);


        while (latency.equals(-10000000L)) {
            //waiting
            System.out.print("");

        }
        //latency is now not -1


        Long controllerLatency2 = latency;

        latency = -10000000L;

        packetSender.sendPacket(0, node_connector_id, node_id);
        while (latency.equals(-10000000L)) {
            //waiting
            System.out.print("");
        }
        Long latency2 = latency - controllerLatency2;

        //jitter is an absolute value
        Long jitter = Math.abs(latency2 - latency1);


        return jitter;


    }

    public static List<Link> getAllLinks() {
        List<Link> linkList = new ArrayList<>();

        try {
            TopologyId topoId = new TopologyId("flow:1");
            InstanceIdentifier<Topology> nodesIid = InstanceIdentifier.builder(NetworkTopology.class).child(Topology.class, new TopologyKey(topoId)).toInstance();
            ReadOnlyTransaction nodesTransaction = db.newReadOnlyTransaction();
            CheckedFuture<Optional<Topology>, ReadFailedException> nodesFuture = nodesTransaction
                    .read(LogicalDatastoreType.OPERATIONAL, nodesIid);
            Optional<Topology> nodesOptional = nodesFuture.checkedGet();

            if (nodesOptional != null && nodesOptional.isPresent())
                linkList = nodesOptional.get().getLink();

            return linkList;
        } catch (Exception e) {


            return linkList;
        }

    }


}
