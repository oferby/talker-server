package com.toga.netbrain;

import com.toga.netbrain.agent.grpc.GrpcClient;
import com.toga.netbrain.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGrpc {

    @Autowired
    private GrpcClient grpcClient;

    @Test
    public void testAgentHostInfo() {

        String host = "localhost";
        AgentHostInfoResponse hostInfo =
                grpcClient.requestAgentHostInfo(host);

        assert hostInfo.getHostId().equals(host);

    }

    @Test
    public void testAddAgent() {

        String host = "localhost";
        String username = "oferb";
        String password = "oferb";

        CreateAgentResponse createAgentResponse = grpcClient.createAgent(host, host, username, password);

        assert createAgentResponse.getAck();
        System.out.println("agent created!");

//    }

//    @Test
//    public void nodeDiscovery() {

//        String host = "localhost";

        NodeDiscoveryResponse response = grpcClient.nodeDiscovery(host);

        assert response.getHost().equals(host);

        System.out.println("Node Information:");

        for (NetElement netElement : response.getNetElementsList()) {
            System.out.println("URI: " + netElement.getURI());
        }


    }

}
