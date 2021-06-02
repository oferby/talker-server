package com.toga.netbrain.model.db.entities.hw;

import com.toga.netbrain.model.db.entities.hw.inf.NetworkInterface;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node({"LineCard","Element"})
public class LineCard extends Hardware {

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private List<NetworkInterface> networkInterfaceList;

    public List<NetworkInterface> getNetworkInterfaceList() {
        return networkInterfaceList;
    }

    public void setNetworkInterfaceList(List<NetworkInterface> networkInterfaceList) {
        this.networkInterfaceList = networkInterfaceList;
    }

    public LineCard addNetworkInterface(NetworkInterface networkInterface) {
        if (networkInterfaceList == null)
            networkInterfaceList = new ArrayList<>();

        networkInterfaceList.add(networkInterface);

        return this;

    }
}
