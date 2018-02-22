package eu.virtuwind.monitoring.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import org.opendaylight.openflowplugin.api.OFConstants;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev100924.Ipv4Prefix;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev100924.Uri;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev100924.MacAddress;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.DropActionCaseBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.OutputActionCaseBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.SetQueueActionCaseBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.drop.action._case.DropActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.output.action._case.OutputActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.set.queue.action._case.SetQueueActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.list.Action;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.list.ActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.FlowCapableNode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.FlowId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.Table;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.TableKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.table.Flow;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.table.FlowBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.table.FlowKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.service.rev130819.*;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.FlowCookie;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.FlowModFlags;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.flow.Instructions;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.flow.InstructionsBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.flow.Match;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.flow.MatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.instruction.ApplyActionsCaseBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.instruction.apply.actions._case.ApplyActions;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.instruction.apply.actions._case.ApplyActionsBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.list.InstructionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.list.Instruction;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.Nodes;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.NodeKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.l2.types.rev130827.EtherType;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.ethernet.match.fields.EthernetSourceBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.ethernet.match.fields.EthernetTypeBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.EthernetMatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.layer._3.match.Ipv4MatchBuilder;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.JdkFutureAdapters;
import com.google.common.util.concurrent.ListenableFuture;


public class SwitchConfigurator {

    private static final Logger LOG = LoggerFactory.getLogger(SwitchConfigurator.class);

    private SalFlowService salFlowService;

    private static SwitchConfigurator switchConfigurator;

    private static long id = 0;

    private static List<Flow> flows = new ArrayList<>();

    public SwitchConfigurator(SalFlowService salFlowService) {
        this.salFlowService = salFlowService;
        switchConfigurator = this;
    }

    public static SwitchConfigurator getInstance() {
        return switchConfigurator;
    }

    private AtomicLong flowCookieInc = new AtomicLong(0x2a00000000000000L);


    private RemoveFlowInput getRemoveFlowInput(Flow flowtoDelete) {


        //identify the flow
        InstanceIdentifier<Flow> flowPath = InstanceIdentifier
                .builder(Nodes.class)
                .child(Node.class, new NodeKey(new NodeId("openflow:1")))
                .augmentation(FlowCapableNode.class)
                .child(Table.class, new TableKey(flowtoDelete.getTableId()))
                .child(Flow.class, new FlowKey(flowtoDelete.getId())).build();


        RemoveFlowInputBuilder b = new RemoveFlowInputBuilder(flowtoDelete);
        InstanceIdentifier<Table> tableInstanceId = flowPath
                .<Table>firstIdentifierOf(Table.class);
        InstanceIdentifier<Node> nodeInstanceId = flowPath
                .<Node>firstIdentifierOf(Node.class);
        b.setNode(new NodeRef(nodeInstanceId));
        b.setFlowTable(new FlowTableRef(tableInstanceId));
        b.setTransactionUri(new Uri(flowtoDelete.getId().getValue()));

        //the flow to remove
        return b.build();
    }


    public boolean removeFlows() {

        List<Long> list1 = new ArrayList<>();

        Integer max = flows.size();
        long[] list = new long[max];
        Long sum = 0L;

        try {

            BufferedWriter out = new BufferedWriter(new FileWriter("del-latency" + max + ".txt"));

            for (int i = 0; i < flows.size(); ++i) {

                Flow flow = flows.get(i);

                long beforeTime = System.currentTimeMillis();
                RemoveFlowInput removeFlowInput = getRemoveFlowInput(flow);
                Future<RpcResult<RemoveFlowOutput>> future = salFlowService.removeFlow(removeFlowInput);
                if(future.get().isSuccessful() || future.get().getErrors().size() == 0) {
                    long afterTime = System.currentTimeMillis();
                    long totalTime = afterTime - beforeTime;
                    list[i] = totalTime;
                    list1.add(totalTime);
                    sum += totalTime;
                }


            }


            System.out.println(list1);
            System.out.println("sum is: " + sum);
            System.out.println("max is: " + max);

            for (int i = 0; i < max; ++i) {
                out.write(list[i] + "\n");
            }
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;


    }


    public void getTimes() {

        List<Long> list1 = new ArrayList<>();

        Integer max = 100;
        long[] list = new long[max];
        Long sum = 0L;


        try {

            BufferedWriter out = new BufferedWriter(new FileWriter("latency" + max + ".txt"));

            for (int i = 0; i < max; i++) {

                long beforeTime = System.currentTimeMillis();
                boolean success = send("openflow:1", "openflow:1:1");
                System.out.println("success is: " + success);
                long afterTime = System.currentTimeMillis();
                long totalTime = afterTime - beforeTime;
                list[i] = totalTime;
                list1.add(totalTime);
                sum += totalTime;

            }


            System.out.println(list1);
            System.out.println("sum is: " + sum);
            System.out.println("max is: " + max);
            System.out.println("average: " + sum / max);
            for (int i = 0; i < max; ++i) {
                out.write(list[i] + "\n");
            }
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public boolean send(String edge_switch, String edge_nodeconnector) {

        LOG.debug("Start executing RPC");

        // create the flow
        Flow createdFlow = createFlow(edge_nodeconnector);

        flows.add(createdFlow);

        // build instance identifier for flow
        InstanceIdentifier<Flow> flowPath = InstanceIdentifier
                .builder(Nodes.class)
                .child(Node.class, new NodeKey(new NodeId(edge_switch)))
                .augmentation(FlowCapableNode.class)
                .child(Table.class, new TableKey(createdFlow.getTableId()))
                .child(Flow.class, new FlowKey(createdFlow.getId())).build();


        final AddFlowInputBuilder builder = new AddFlowInputBuilder(createdFlow);
        final InstanceIdentifier<Table> tableInstanceId = flowPath
                .<Table>firstIdentifierOf(Table.class);
        final InstanceIdentifier<Node> nodeInstanceId = flowPath
                .<Node>firstIdentifierOf(Node.class);
        builder.setNode(new NodeRef(nodeInstanceId));
        builder.setFlowTable(new FlowTableRef(tableInstanceId));
        builder.setTransactionUri(new Uri(createdFlow.getId().getValue()));
        final AddFlowInput flow = builder.build();


        Future<RpcResult<AddFlowOutput>> future = salFlowService.addFlow(flow);

        try {
            if (future.get().isSuccessful()) {
                return true;
            } else if (future.get().getErrors().size() == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    private String randomMACAddress() {
        Random rand = new Random();
        byte[] macAddr = new byte[6];
        rand.nextBytes(macAddr);

        macAddr[0] = (byte) (macAddr[0] & (byte) 254);  //zeroing last 2 bytes to make it unicast and locally adminstrated

        StringBuilder sb = new StringBuilder(18);
        for (byte b : macAddr) {

            if (sb.length() > 0)
                sb.append(":");

            sb.append(String.format("%02x", b));
        }


        return sb.toString();
    }


    /**
     * Adds an application flow by using the REST API.
     */
    private Flow createFlow(String edge_nodeconnector) {


        ++id;
        String macaddress = randomMACAddress();

        FlowBuilder flowBuilder = new FlowBuilder() //
                .setTableId((short) 0) //
                .setFlowName("random");

        //
        flowBuilder.setId(new FlowId(Long.toString(flowBuilder.hashCode())));

        Match match = new MatchBuilder()

                .setEthernetMatch(new EthernetMatchBuilder()
                        .setEthernetSource(new EthernetSourceBuilder().setAddress(new MacAddress(macaddress)).build())
                        .setEthernetType(new EthernetTypeBuilder()
                                .setType(new EtherType(0x0800L))
                                .build())
                        .build())
                .build();

        ActionBuilder actionBuilder = new ActionBuilder();
        List<Action> actions = new ArrayList<Action>();

        //Actions
        //currently changing tos and sending to output connector


        Action drop = actionBuilder.setOrder(1).setAction(new DropActionCaseBuilder()
                .setDropAction(new DropActionBuilder()
                        .build())
                .build()).build();


        actions.add(drop);

        //ApplyActions
        ApplyActions applyActions = new ApplyActionsBuilder().setAction(actions).build();

        //Instruction
        Instruction applyActionsInstruction = new InstructionBuilder() //
                .setOrder(0).setInstruction(new ApplyActionsCaseBuilder()//
                        .setApplyActions(applyActions) //
                        .build()) //
                .build();

        Instructions applyInstructions = new InstructionsBuilder()
                .setInstruction(ImmutableList.of(applyActionsInstruction))
                .build();

        // Put our Instruction in a list of Instructions
        flowBuilder
                .setMatch(match)
                .setId(new FlowId(String.valueOf(id)))
                .setBufferId(OFConstants.OFP_NO_BUFFER)
                .setInstructions(applyInstructions)
                .setPriority(1000)
                .setHardTimeout(0)
                .setIdleTimeout(0)
                //.setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
                .setFlags(new FlowModFlags(false, false, false, false, false));

        return flowBuilder.build();
    }
}