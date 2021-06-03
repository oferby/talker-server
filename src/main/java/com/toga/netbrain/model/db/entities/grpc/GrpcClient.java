package com.toga.netbrain.model.db.entities.grpc;

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

    private Map<String, AgentServiceGrpc.AgentServiceBlockingStub> stubMap = new HashMap<>();
    private Map<String, String> agentTargetMap = new HashMap<>();

//    private AgentServiceGrpc.AgentServiceStub agentServiceStub;

    public AgentHostInfoResponse requestAgentHostInfo(String agent) {

        AgentServiceGrpc.AgentServiceBlockingStub blockingStub = this.getBlockingStub(agent);

        AgentHostInfoRequest request = AgentHostInfoRequest.newBuilder().setHostId(agent).build();

        AgentHostInfoResponse hostInfo = blockingStub.getHostInfo(request);

        logger.debug("hostInfo: " + hostInfo);

        return hostInfo;

    }

    public CreateAgentResponse createAgent(String agent, String target, String username, String password) {

        agentTargetMap.put(target, agent);

        AgentServiceGrpc.AgentServiceBlockingStub blockingStub = this.getBlockingStub(agent);

        CreateAgentRequest createAgentRequest = CreateAgentRequest.newBuilder()
                .setHost(target)
                .setUsername(username)
                .setPassword(password)
                .build();

        CreateAgentResponse response = blockingStub.createAgent(createAgentRequest);

        logger.debug(response.toString());

        return response;
    }

    public NodeDiscoveryResponse nodeDiscovery(String target) {

        String agent = agentTargetMap.get(target);
        AgentServiceGrpc.AgentServiceBlockingStub blockingStub = this.getBlockingStub(agent);

        NodeDiscoveryRequest nodeDiscoveryRequest = NodeDiscoveryRequest.newBuilder()
                .setHost(target).build();

        NodeDiscoveryResponse response = blockingStub.runDiscovery(nodeDiscoveryRequest);

        logger.debug(response.toString());

        return response;
    }


    private AgentServiceGrpc.AgentServiceBlockingStub getBlockingStub(String agent) {

        AgentServiceGrpc.AgentServiceBlockingStub blockingStub;

        if (stubMap.containsKey(agent)) {
            blockingStub = stubMap.get(agent);

        } else {

            ManagedChannelBuilder<?> channelBuilder =
                    ManagedChannelBuilder.forAddress(agent, 51051).usePlaintext();
            ManagedChannel channel = channelBuilder.build();

            blockingStub = AgentServiceGrpc.newBlockingStub(channel);

            stubMap.put(agent, blockingStub);

        }

        return blockingStub;

    }

    @PostConstruct
    private void setup() {

//        agentServiceStub = AgentServiceGrpc.newStub(channel);

    }


}
