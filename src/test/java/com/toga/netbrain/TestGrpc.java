package com.toga.netbrain;

import com.toga.netbrain.agent.HostAgentManager;
import com.toga.netbrain.agent.grpc.GrpcClient;
import com.toga.netbrain.model.db.controller.NodeEntityRepository;
import com.toga.netbrain.model.db.entities.management.DeviceAgent;
import com.toga.netbrain.model.db.entities.management.HostAgent;
import com.toga.netbrain.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGrpc {

    @Autowired
    private GrpcClient grpcClient;

    @Autowired
    private HostAgentManager hostAgentManager;

    @Autowired
    private NodeEntityRepository nodeEntityRepository;

    private String hostAgent = "localhost";
    private String target = "localhost";
    private String username = "oferb";
    private String password = "oferb";

    @Test
    public void testAgentHostInfo() {

        hostAgentManager.addHostAgent(hostAgent);

        Optional<HostAgent> agentByName = nodeEntityRepository.findHostAgentByName(hostAgent);

        assert agentByName.isPresent();
        assert agentByName.get().getHostName() != null;

    }

    @Test
    public void deleteHostAgent() {

        hostAgentManager.deleteHostAgent(hostAgent);

        Optional<HostAgent> hostAgentByName = nodeEntityRepository.findHostAgentByName(hostAgent);
        assert hostAgentByName.isEmpty();

    }


    @Test
    public void testAddAgent() {

        hostAgentManager.addAgent(hostAgent, target, username, password);

        Optional<DeviceAgent> deviceAgentByTarget = nodeEntityRepository.findDeviceAgentByTarget(target);

        assert deviceAgentByTarget.isPresent();


//    }

//    @Test
//    public void nodeDiscovery() {

//        String host = "localhost";
//
        NodeDiscoveryResponse response = grpcClient.getAgentInformation(target);

        assert response.getTarget().equals(target);

        System.out.println("Node Information:");

        for (NetElement netElement : response.getNetElementsList()) {
            System.out.println("URI: " + netElement.getURI());
        }


    }

}
