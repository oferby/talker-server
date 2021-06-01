package com.toga.talker.model.db.entities.hw;

import com.toga.talker.model.db.entities.hw.inf.NetworkInterface;
import com.toga.talker.model.db.entities.hw.inf.PortNumberProperty;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

public class LineCard extends Hardware {

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private List<PortNumberProperty> portNumberProperties;

    public List<PortNumberProperty> getPortNumberProperties() {
        return portNumberProperties;
    }

    public void setPortNumberProperties(List<PortNumberProperty> portNumberProperties) {
        this.portNumberProperties = portNumberProperties;
    }

    public LineCard addNetworkInterface(NetworkInterface networkInterface, String portId) {
        if (portNumberProperties == null)
            portNumberProperties = new ArrayList<>();

        PortNumberProperty portNumberProperty = new PortNumberProperty()
                .setPortId(portId)
                .setNetworkInterface(networkInterface);
        portNumberProperties.add(portNumberProperty);

        return this;

    }
}
