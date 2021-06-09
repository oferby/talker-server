package com.toga.netbrain.agent;

import com.toga.netbrain.agent.grpc.GrpcClient;
import com.toga.netbrain.model.db.controller.NodeEntityRepository;
import com.toga.netbrain.model.db.entities.EntityExistException;
import com.toga.netbrain.model.db.entities.EntityNotFoundException;
import com.toga.netbrain.model.db.entities.management.DeviceAgent;
import com.toga.netbrain.model.db.entities.management.HostAgent;
import com.toga.netbrain.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Controller
public class HostAgentManager {

    private static final Logger logger = LoggerFactory.getLogger(HostAgentManager.class);

    @Autowired
    private NodeEntityRepository nodeEntityRepository;

    @Autowired
    private GrpcClient grpcClient;

    @Autowired
    private TaskExecutor taskExecutor;

    public void addHostAgent(String host) {

        if (nodeEntityRepository.findHostAgentByName(host).isPresent())
            throw new EntityExistException();

        HostAgent hostAgent = new HostAgent();
        hostAgent.setName(host);
        hostAgent = nodeEntityRepository.save(hostAgent);

        AgentHostInfoResponse hostAgentInfo = grpcClient.requestHostAgentInfo(hostAgent);
        hostAgent.setHostName(hostAgentInfo.getHostName());

        nodeEntityRepository.save(hostAgent);


    }

    public void deleteHostAgent(String host) {
        Optional<HostAgent> hostAgent = nodeEntityRepository.findHostAgentByName(host);

        if (hostAgent.isEmpty())
            throw new EntityNotFoundException("could not find host to delete");

        grpcClient.shutDownHostAgent(hostAgent.get());
        nodeEntityRepository.delete(hostAgent.get());
    }


    public void addAgent(String target, String username, String password) {

        List<HostAgent> allHostAgents = nodeEntityRepository.findAllHostAgents();

        if (allHostAgents.isEmpty())
            throw new EntityNotFoundException();

        HostAgent hostAgent = allHostAgents.get(0);
        addAgent(hostAgent, target, username, password);

    }

    public void addAgent(String host, String target, String username, String password) {

        Optional<HostAgent> hostAgentByName = nodeEntityRepository.findHostAgentByName(host);

        if (hostAgentByName.isEmpty())
            throw new EntityNotFoundException();

        HostAgent hostAgent = hostAgentByName.get();
        addAgent(hostAgent, target, username, password);
    }

    private void addAgent(HostAgent hostAgent, String target, String username, String password) {

        hostAgent.addDeviceAgent(new DeviceAgent(target, username, password));
        nodeEntityRepository.save(hostAgent);

        CreateAgentResponse createAgentResponse = grpcClient.createAgent(hostAgent.getId(), target, username, password);

        if (createAgentResponse.getAck()) {
            taskExecutor.execute(() -> {
                runAgentDiscovery(hostAgent.getName(), target);
            });
        }

    }


    public void deleteDeviceAgent(String target) {

        DeleteAgentResponse deleteAgentResponse = grpcClient.deleteAgent(target);
        if (deleteAgentResponse.getAck()) {
            nodeEntityRepository.deleteDeviceAgentByTarget(target);
            return;
        }

        throw new RuntimeException("agent not deleted");

    }

    private void runAgentDiscovery(String host, String agent) {

        NodeDiscoveryResponse response = grpcClient.getAgentInformation(agent);

        for (NetElement netElement : response.getNetElementsList()) {
            String uri = netElement.getURI();

        }


    }


    @PostConstruct
    private void getAllAgentsFromDB() {

        List<HostAgent> hostAgents = nodeEntityRepository.findAllHostAgents();
        grpcClient.loadAgents(hostAgents);

    }



}
