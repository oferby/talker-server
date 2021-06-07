package com.toga.netbrain.agent;

import com.toga.netbrain.model.db.controller.NodeEntityRepository;
import com.toga.netbrain.model.db.entities.EntityExistException;
import com.toga.netbrain.model.db.entities.EntityNotFoundException;
import com.toga.netbrain.model.db.entities.management.DeviceAgent;
import com.toga.netbrain.model.db.entities.management.HostAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Controller
public class AgentHostManager {

    private static final Logger logger = LoggerFactory.getLogger(AgentHostManager.class);

    @Autowired
    private NodeEntityRepository nodeEntityRepository;

    public void addHostAgent(String host) {

        if (nodeEntityRepository.findHostAgentByName(host).isPresent())
            throw new EntityExistException();

        HostAgent hostAgent = new HostAgent();
        hostAgent.setName(host);
        nodeEntityRepository.save(hostAgent);

    }

    public void deleteHostAgent(String host) {
        nodeEntityRepository.deleteHostAgent(host);
    }

    public HostAgent getHostAgentByTarget(String target) {

        return nodeEntityRepository.findHostAgentByTarget(target);

    }

    public void addAgent(String target, String username, String password) {

        Optional<HostAgent> hostAgentByName = nodeEntityRepository.findHostAgentByName("");

        if (hostAgentByName.isEmpty())
            throw new EntityNotFoundException();

        HostAgent hostAgent = hostAgentByName.get();
        hostAgent.addDeviceAgent(new DeviceAgent(target, username, password));
        nodeEntityRepository.save(hostAgent);
    }

    public void addAgent(String host, String target, String username, String password) {

        Optional<HostAgent> hostAgentByName = nodeEntityRepository.findHostAgentByName(host);

        if (hostAgentByName.isEmpty())
            throw new EntityNotFoundException();
        HostAgent hostAgent = hostAgentByName.get();
        hostAgent.addDeviceAgent(new DeviceAgent(target, username, password));
        nodeEntityRepository.save(hostAgent);
    }

    public void deleteDeviceAgent(String target) {
        nodeEntityRepository.deleteDeviceAgentByTarget(target);
    }

    @PostConstruct
    private void getAllAgentsFromDB() {


    }


}
