package eu.virtuwind.monitoring.impl;


import  eu.virtuwind.monitoring.impl.external.*;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.sal.binding.api.NotificationProviderService;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.impl.rev150722.modules.module.configuration.monitoring.impl.NotificationService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketProcessingListener;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketProcessingService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketReceived;
import org.opendaylight.yangtools.concepts.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.*;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class PacketProcessing implements PacketProcessingListener {


    private final Logger LOG = LoggerFactory.getLogger(PacketProcessing.class);
    private final static long FLOOD_PORT_NUMBER = 0xfffffffbL;
    private List<Registration> registrations;
    private DataBroker dataBroker;
    private PacketProcessingService packetProcessingService;
    private List<String> dstMacs;
    private NotificationProviderService notificationProviderService;


    public PacketProcessing() {
        LOG.info("PacketProcessing loaded successfully");
        dstMacs = new LinkedList<>();
    }


    @Override
    public void onPacketReceived(final PacketReceived packetReceived) {

        LatencyPacket latencyPacket = new LatencyPacketBuilder().setLatency(BigInteger.TEN).build();

        notificationProviderService.publish(latencyPacket);


        new Thread(new Runnable() {
            @Override
            public void run() {

                byte[] payload = packetReceived.getPayload();


                if(payload.length == 0) {

                    System.out.println("\n\n\n\n" + "packet length 0");
                    Long timeNow = System.nanoTime();

                    //    System.out.println("received timed: " + timeNow);

                    Long packetSentTime = PacketSender.sentTime;
                    //System.out.println("sent time here for received----->" + packetSentTime);

                    Long latency = timeNow - packetSentTime;

                    LatencyMonitor.latency = latency;

                }

               // System.out.println("packet payload is::::::::" + payload.length);



              /*  System.out.println(" ");
                for (int i = 0; i < payload.length; i++) {
                    System.out.print(payload[i] + ", ");
                }
                System.out.println(); */

                byte[] srcMacRaw = PacketParsingUtils.extractSrcMac(payload);
                String srcMac = PacketParsingUtils.rawMacToString(srcMacRaw);


                if (srcMac.equals("BA:DB:AD:BA:DB:AD")) {

                     System.out.println("pacekt addrwess matched");

                    Long timeNow = System.nanoTime();

                    //    System.out.println("received timed: " + timeNow);

                    Long packetSentTime = PacketSender.sentTime;
                    //System.out.println("sent time here for received----->" + packetSentTime);

                    Long latency = timeNow - packetSentTime;

                    LatencyMonitor.latency = latency;


            System.out.println("latency is------> " + latency );
            /*


            System.out.println("latency monitors latency is this---->" +  LatencyMonitor.latency);
*/
                }

      /*     LOG.info("src mac -----------"+srcMac);

        byte[] dstMacRaw = PacketParsingUtils.extractDstMac(payload);
        String dstMac = PacketParsingUtils.rawMacToString(dstMacRaw);
        //LOG.info("dest mac ------------"+dstMac);
        String protocol;
        byte p = PacketParsingUtils.extractIPprotocol(payload);
        if (p == 0x11)
            protocol = "UDP";
        else
            protocol = "TCP";

        int port = PacketParsingUtils.extractDestPort(payload);

     if (isDestination(dstMac)) {
            forwardPacket(srcMac, dstMac, srcIp, dstIp);
        }
*/

            }
        }).start();


    }


    public void addDestMac(String address) {
        dstMacs.add(address);
    }

    public boolean isDestination(String address) {
        for (String mac : dstMacs) {
            if (mac.equals(address)) {
                return true;
            }
        }
        return false;
    }

    public void forwardPacket(String scrMac, String dstMac, String scrIp, String dstIp) {
        /**
         NodeConnectorRef ingressNodeConnectorRef = packetReceived.getIngress();
         NodeRef ingressNodeRef = InventoryUtils.getNodeRef(ingressNodeConnectorRef);
         NodeConnectorId ingressNodeConnectorId = InventoryUtils.getNodeConnectorId(ingressNodeConnectorRef);
         NodeId ingressNodeId = InventoryUtils.getNodeId(ingressNodeConnectorRef);
         */
        LOG.info("Forwarded packet " + dstMac);
    }

}