package com.toga.talker.model.db.entities.hw.inf;

import com.toga.talker.model.db.entities.RelationshipPropertyElement;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class PortNumberProperty extends RelationshipPropertyElement {

    @Property
    private String portId;

    @TargetNode
    private NetworkInterface networkInterface;

    public String getPortId() {
        return portId;
    }

    public PortNumberProperty setPortId(String portId) {
        this.portId = portId;
        return this;
    }

    public NetworkInterface getNetworkInterface() {
        return networkInterface;
    }

    public PortNumberProperty setNetworkInterface(NetworkInterface networkInterface) {
        this.networkInterface = networkInterface;
        return this;
    }
}
