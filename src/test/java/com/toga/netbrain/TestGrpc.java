package com.toga.netbrain;

import com.toga.netbrain.service.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGrpc {

    private AgentServiceGrpc.AgentServiceBlockingStub blockingStub;
    private AgentServiceGrpc.AgentServiceStub agentServiceStub;

    @PostConstruct
    private void setup() {
        ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forAddress("localhost", 51051).usePlaintext();
        ManagedChannel channel = channelBuilder.build();

        blockingStub = AgentServiceGrpc.newBlockingStub(channel);
        agentServiceStub = AgentServiceGrpc.newStub(channel);

    }

    @Test
    public void testAgentHostInfo() {


        AgentHostInfoRequest request = AgentHostInfoRequest.newBuilder().setHostId("1").build();

        AgentHostInfoResponse hostInfo = blockingStub.getHostInfo(request);

        assert hostInfo != null;
        System.out.println("Agent Hostname: " + hostInfo.getHostName());
        System.out.println("Agents: " + hostInfo.getNumOfAgents());

        CreateAgentRequest createAgentRequest = CreateAgentRequest.newBuilder()
                .setHost("localhost")
                .setUsername("oferb")
                .setPassword("oferb")
                .build();

        CreateAgentResponse createAgentResponse = blockingStub.createAgent(createAgentRequest);

        assert createAgentResponse.getAck();
        System.out.println("agent created!");

        hostInfo = blockingStub.getHostInfo(request);
        System.out.println("Agents: " + hostInfo.getNumOfAgents());

    }

    @Test
    public void nodeDiscovery() {

        String host = "localhost";
        NodeDiscoveryRequest nodeDiscoveryRequest = NodeDiscoveryRequest.newBuilder()
                .setHost(host).build();

        NodeDiscoveryResponse response = blockingStub.runDiscovery(nodeDiscoveryRequest);

        assert response.getHost().equals(host);

        System.out.println("Node Information:");

        for (NetElement netElement : response.getNetElementsList()) {
            System.out.println("URI: " + netElement.getURI());
        }


    }

}
