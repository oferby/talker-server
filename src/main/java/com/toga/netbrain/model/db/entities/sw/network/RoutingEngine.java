package com.toga.netbrain.model.db.entities.sw.network;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node({"RoutingEngine","Element","NetworkService"})
public class RoutingEngine extends NetworkService {

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
