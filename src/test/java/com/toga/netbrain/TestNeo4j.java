package com.toga.netbrain;

import com.toga.netbrain.agent.HostAgentManager;
import com.toga.netbrain.model.db.controller.NodeEntityRepository;
import com.toga.netbrain.model.db.entities.DataSizeUnits;
import com.toga.netbrain.model.db.entities.Element;
import com.toga.netbrain.model.db.entities.hw.*;
import com.toga.netbrain.model.db.entities.hw.inf.EthernetPort;
import com.toga.netbrain.model.db.entities.management.DeviceAgent;
import com.toga.netbrain.model.db.entities.management.HostAgent;
import com.toga.netbrain.model.db.entities.org.Vendor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestNeo4j {

    @Autowired
    private NodeEntityRepository nodeEntityRepository;

    @Autowired
    private HostAgentManager hostAgentManager;

    @Test
    public void testFindAll() {

        assert nodeEntityRepository != null;

        List<Element> all = nodeEntityRepository.findAll();

        assert !all.isEmpty();

    }

    @Test
    public void deleteAll() {
        nodeEntityRepository.deleteAll();

        List<Element> all = nodeEntityRepository.findAll();

        assert all.isEmpty();

    }

    @Test
    public void testAddNode() {

        Host host = new Host();
        CPU cpu = new CPU();
        cpu.setVendor(new Vendor().setName("Intel"));
        host.setCpu(cpu);
        host.setMemory(new Memory().setAmount(1).setDataSizeUnits(DataSizeUnits.GB));

        Host host1 = nodeEntityRepository.save(host);
        Long id = host1.getId();

        assert id != null;

        Optional<Element> hostById = nodeEntityRepository.findById(id);

        assert hostById.isPresent();

        nodeEntityRepository.deleteById(id);

        hostById = nodeEntityRepository.findById(id);

        assert hostById.isEmpty();

    }

    @Test
    public void testBuildChassis() {

        Chassis chassis = new Chassis().setVendor(new Vendor().setName("Cisco"));

        for (int i = 0; i < 5; i++) {

            LineCard lineCard = new LineCard();

            for (int j = 0; j < 4; j++) {

                lineCard.addNetworkInterface(new EthernetPort()
                        .setOperationalStatus(OperationalStatus.UP)
                        .setName("eth" + j)
                );

            }

            chassis.addLineCard(lineCard, "lc-" + i);

        }


        Chassis save = nodeEntityRepository.save(chassis);
        Long id = save.getId();

        Optional<Element> optional = nodeEntityRepository.findById(id);

        assert optional.isPresent();

        nodeEntityRepository.deleteById(id);

        optional = nodeEntityRepository.findById(id);

        assert optional.isEmpty();

    }


    @Test
    public void addAgents() {

        String name = "hostAgent2";
        HostAgent hostAgent = new HostAgent();
        hostAgent.setName(name);

        HostAgent save = nodeEntityRepository.save(hostAgent);

        Optional<HostAgent> agentByName = nodeEntityRepository.findHostAgentByName(name);

        assert agentByName.isPresent();

        HostAgent hostAgentByName = agentByName.get();

        String target = "target";
        String username = "username";
        String password = "passwd";
        for (int i = 0; i < 10; i++) {
            DeviceAgent deviceAgent = new DeviceAgent(target + (20 + i), username, password);

            hostAgentByName.addDeviceAgent(deviceAgent);

        }

        HostAgent agent = nodeEntityRepository.save(hostAgentByName);

        assert agent.getDeviceAgentList() != null;

    }


    @Test
    public void testAgents() {

        String name = "hostAgent1";
        String target = "target";

        HostAgent hostAgentByTarget = nodeEntityRepository.findHostAgentByTarget(target + 1);

        assert hostAgentByTarget != null;

        nodeEntityRepository.deleteHostAgent(name);

        Optional<HostAgent> agentByName = nodeEntityRepository.findHostAgentByName(name);

        assert agentByName.isPresent();

    }

    @Test
    public void deleteDeviceAgent() {
        nodeEntityRepository.deleteDeviceAgentByTarget("target1");

        DeviceAgent target1 = nodeEntityRepository.findDeviceAgentByTarget("target1");

        assert target1 == null;
    }

    @Test
    public void testNumberOfAgents() {

        List<HostAgent> allHostAgents = nodeEntityRepository.findAllHostAgents();

        assert allHostAgents != null;

        System.out.println(allHostAgents);
    }

    @Test
    public void testAddAgentToHost() {

//        List<HostAgent> allHostAgents = nodeEntityRepository.findAllHostAgents();

        hostAgentManager.addAgent("target99", "u1", "p1");

        HostAgent hostAgent = nodeEntityRepository.findHostAgentByTarget("target99");

        assert hostAgent.getDeviceAgentList().size() > 1;

    }


}
