package eu.virtuwind.monitoring.impl;


import eu.virtuwind.monitoring.impl.external.*;
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


    public PacketProcessing(NotificationProviderService nps) {
        LOG.info("PacketProcessing loaded successfully");
        dstMacs = new LinkedList<>();
        notificationProviderService = nps;
    }


    @Override
    public void onPacketReceived(final PacketReceived packetReceived) {


        new Thread(new Runnable() {
            @Override
            public void run() {


                byte[] payload = packetReceived.getPayload();


                if (payload.length == 0) {

                    Long timeNow = System.nanoTime();

                    Long packetSentTime = PacketSender.sentTime;

                    Long latency = timeNow - packetSentTime;

                    LatencyMonitor.latency = latency;

                }


                byte[] srcMacRaw = PacketParsingUtils.extractSrcMac(payload);
                String srcMac = PacketParsingUtils.rawMacToString(srcMacRaw);


                if (srcMac.equals("BA:DB:AD:BA:DB:AD")) {



                    Long timeNow = System.nanoTime();



                    Long packetSentTime = PacketSender.sentTime;


                    Long latency = timeNow - packetSentTime;

                    LatencyMonitor.latency = latency;


                }


            }
        }).start();


    }





}