package com.toga.netbrain;

import com.toga.netbrain.service.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGrpc {

    @Test
    public void testAgentHostInfo() {

        ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forAddress("localhost", 51051).usePlaintext();
        ManagedChannel channel = channelBuilder.build();

        AgentServiceGrpc.AgentServiceBlockingStub blockingStub = AgentServiceGrpc.newBlockingStub(channel);
        AgentServiceGrpc.AgentServiceStub agentServiceStub = AgentServiceGrpc.newStub(channel);

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

}
