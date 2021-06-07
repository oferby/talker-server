package com.toga.netbrain.model.db.entities.management;

import com.toga.netbrain.model.db.entities.Element;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;


@Node({"HostAgent","Element"})
public class HostAgent extends Element {

    private String name;

    @Relationship(value = "MANAGE", direction = Relationship.Direction.OUTGOING)
    private List<DeviceAgent> deviceAgentList;

    public HostAgent() {}

    public HostAgent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeviceAgent> getDeviceAgentList() {
        return deviceAgentList;
    }

    public void setDeviceAgentList(List<DeviceAgent> deviceAgentList) {
        this.deviceAgentList = deviceAgentList;
    }

    public void addDeviceAgent(DeviceAgent deviceAgent) {
        if (deviceAgentList == null)
            deviceAgentList = new ArrayList<>();

        deviceAgentList.add(deviceAgent);

    }
}
