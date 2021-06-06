package com.toga.netbrain.agent;

import com.toga.netbrain.model.db.entities.Element;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.ArrayList;
import java.util.List;

@Node({"NetBrainAgentHost","Element"})
public class NetBrainAgentHost extends Element {

    @Property
    private String name;

    @Property
    private String hostAddress;

    @Property
    private List<NetBrainAgent> netBrainAgentList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NetBrainAgent> getNetBrainAgentList() {
        return netBrainAgentList;
    }

    public void setNetBrainAgentList(List<NetBrainAgent> netBrainAgentList) {
        this.netBrainAgentList = netBrainAgentList;
    }

    public void addAgent(NetBrainAgent agent) {
        if (netBrainAgentList == null)
            netBrainAgentList = new ArrayList<>();
        netBrainAgentList.add(agent);
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }
}
