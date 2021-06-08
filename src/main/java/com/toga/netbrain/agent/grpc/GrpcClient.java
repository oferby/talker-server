package com.toga.netbrain.agent.grpc;

import com.toga.netbrain.model.db.entities.management.HostAgent;
import com.toga.netbrain.service.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Controller
public class GrpcClient {

    private final Logger logger = LoggerFactory.getLogger(GrpcClient.class);

    private Map<Long, HostAgent> hostAgentMap = new HashMap<>();
    private Map<Long, AgentServiceGrpc.AgentServiceBlockingStub> stubMap = new HashMap<>();
    private Map<Long, Long> targetToHostMap = new HashMap<>();

//    private AgentServiceGrpc.AgentServiceStub agentServiceStub;

    public AgentHostInfoResponse requestHostAgentInfo(HostAgent hostAgent) {

        addHostAgentToMap(hostAgent);

        AgentHostInfoRequest request = AgentHostInfoRequest.newBuilder().setHostId(hostAgent.getId()).build();
        AgentHostInfoResponse hostInfo = stubMap.get(hostAgent.getId()).getHostInfo(request);

        logger.debug("hostInfo: " + hostInfo);

        return hostInfo;

    }

    public void shutDownHostAgent(HostAgent hostAgent) {

        HostAgentResponse response = this.stubMap.get(hostAgent.getId())
                .sendCommand(HostAgentRequest.newBuilder().setCommand("shutDownHostAgent").build());

        if (response.getErrorCode() != 0)
            throw new RuntimeException("problem shutting down host: " + hostAgentMap.get(hostAgent.getName()));

    }

    public CreateAgentResponse createAgent(Long hostAgentId, Long targetId, String username, String password) {

        targetToHostMap.put(targetId, hostAgentId);

        CreateAgentRequest createAgentRequest = CreateAgentRequest.newBuilder()
                .setAgentId(targetId)
                .setUsername(username)
                .setPassword(password)
                .build();

        CreateAgentResponse response = stubMap.get(hostAgentId).createAgent(createAgentRequest);

        logger.debug(response.toString());

        return response;
    }

    public NodeDiscoveryResponse nodeDiscovery(Long targetId) {
        logger.debug("running discovery for agent id: " + targetId);

        Long hostId = targetToHostMap.get(targetId);

        NodeDiscoveryRequest nodeDiscoveryRequest = NodeDiscoveryRequest.newBuilder()
                .setAgentId(targetId).build();

        NodeDiscoveryResponse response = stubMap.get(hostId).runDiscovery(nodeDiscoveryRequest);

        logger.debug(response.toString());

        return response;
    }

    public DeleteAgentResponse deleteAgent(Long deviceAgentId) {
        logger.debug("got delete agent request to device ID: " + deviceAgentId);

        Long hostAgentId = targetToHostMap.get(deviceAgentId);

        DeleteAgentRequest request = DeleteAgentRequest.newBuilder().setAgentId(deviceAgentId).build();

        return stubMap.get(hostAgentId).deleteAgent(request);

    }

    private void addHostAgentToMap(HostAgent hostAgent) {

        hostAgentMap.put(hostAgent.getId(), hostAgent);

        AgentServiceGrpc.AgentServiceBlockingStub blockingStub;
        ManagedChannelBuilder<?> channelBuilder =
                ManagedChannelBuilder.forAddress(hostAgent.getName(), 51051).usePlaintext();
        ManagedChannel channel = channelBuilder.build();

        blockingStub = AgentServiceGrpc.newBlockingStub(channel);

        stubMap.put(hostAgent.getId(), blockingStub);

    }

    @PostConstruct
    private void setup() {

//        agentServiceStub = AgentServiceGrpc.newStub(channel);

    }


}
