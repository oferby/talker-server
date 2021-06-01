package com.toga.talker.model.db.entities.sw;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node
public class RoutingEngine extends NetworkProcess{

    @Property
    private RoutingTable mainRoutingTable;

    public RoutingTable getMainRoutingTable() {
        return mainRoutingTable;
    }

    public RoutingEngine setMainRoutingTable(RoutingTable mainRoutingTable) {
        this.mainRoutingTable = mainRoutingTable;
        return this;
    }
}
