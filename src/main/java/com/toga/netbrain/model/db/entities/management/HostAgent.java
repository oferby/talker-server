package com.toga.netbrain.model.db.entities.management;

import com.toga.netbrain.model.db.entities.Element;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;


@Node
public class HostAgent extends Element implements Comparable<HostAgent>{

    private String hostName;

    @Relationship(value = "MANAGE", direction = Relationship.Direction.OUTGOING)
    private List<DeviceAgent> deviceAgentList;

    public HostAgent() {}

    public HostAgent(String name) {
        super(name);
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

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Override
    public int compareTo(HostAgent that) {

        int thatSize;
        if (that.deviceAgentList == null || that.deviceAgentList.isEmpty())
            thatSize = 0;
        else
            thatSize = that.deviceAgentList.size();

        int thisSize;
        if (this.deviceAgentList == null || this.deviceAgentList.isEmpty())
            thisSize = 0;
        else
            thisSize = this.deviceAgentList.size();

        return Integer.compare(thisSize, thatSize);

    }

    @Override
    public String toString() {
        return "HostAgent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hostName='" + hostName + '\'' +
                ", deviceAgentList=" + deviceAgentList +
                '}';
    }
}
