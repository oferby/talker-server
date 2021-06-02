package com.toga.netbrain.model.db.entities.agent;

import com.toga.netbrain.model.db.NodeEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AgentHostManager {

    private Map<String, NetBrainAgentHost> agentHostManagerMap = new HashMap<>();

    @Autowired
    private NodeEntityRepository nodeEntityRepository;

    public void addAgentHost(String hostAddress) {

        NetBrainAgentHost agentHost = new NetBrainAgentHost();
        agentHost.setHostAddress(hostAddress);
        agentHostManagerMap.put(hostAddress, agentHost);

        nodeEntityRepository.save(agentHost);


    }

    @PostConstruct
    private void getAllAgentsFromDB() {




    }


}
