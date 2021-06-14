package com.toga.netbrain;

import com.toga.netbrain.agent.HostAgentManager;
import com.toga.netbrain.model.db.controller.NodeEntityRepository;
import com.toga.netbrain.model.db.entities.management.DeviceAgent;
import com.toga.netbrain.model.db.entities.management.HostAgent;
import com.toga.netbrain.service.NetElement;
import com.toga.netbrain.service.NodeDiscoveryResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAgent {

    @Autowired
    private HostAgentManager hostAgentManager;

    @Autowired
    private NodeEntityRepository nodeEntityRepository;

    private String host = "localhost";
    private String agent = "localhost";
    private String target = "10.100.99.187";
    private String username = "oferb";
    private String password = "123456";

    @Test
    public void testAddHost() {

        hostAgentManager.addHostAgent(host);

        Optional<HostAgent> hostAgent = nodeEntityRepository.findHostAgentByName(host);

        assert hostAgent.isPresent();


    }

    @Test
    public void deleteHostAgent() {

        hostAgentManager.deleteHostAgent(host);

        Optional<HostAgent> hostAgent = nodeEntityRepository.findHostAgentByName(host);

        assert hostAgent.isEmpty();

    }

    @Test
    public void testAddAgent() {

        hostAgentManager.addAgent(target, username, password);

        Optional<DeviceAgent> deviceAgentByTarget = nodeEntityRepository.findDeviceAgentByTarget(target);

        assert deviceAgentByTarget.isPresent();


    }





    @Test
    public void testDeleteAgent() {

        hostAgentManager.deleteDeviceAgent(target);

        Optional<DeviceAgent> deviceAgentByTarget = nodeEntityRepository.findDeviceAgentByTarget(target);

        assert deviceAgentByTarget.isEmpty();


    }


}
