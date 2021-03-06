package com.toga.netbrain.model.db.entities.hw;

import com.toga.netbrain.model.db.entities.hw.inf.NetworkInterface;
import com.toga.netbrain.model.db.entities.hw.inf.PortNumberProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
public class LineCard extends Hardware {

    public LineCard() {
    }

    public LineCard(String name) {
        this.name = name;
    }

    @Relationship(type = "HAS")
    private List<NetworkInterface> networkInterfaceList;

    public List<NetworkInterface> getNetworkInterfaceList() {
        return networkInterfaceList;
    }

    public void setNetworkInterfaceList(List<NetworkInterface> networkInterfaceList) {
        this.networkInterfaceList = networkInterfaceList;
    }

    public void addNetworkInterface(NetworkInterface networkInterface) {
        if (networkInterfaceList == null)
            networkInterfaceList = new ArrayList<>();

        networkInterfaceList.add(networkInterface);

    }
}
