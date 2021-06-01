package com.toga.talker.model.db.entities.hw.inf;

import com.toga.talker.model.db.entities.Element;
import com.toga.talker.model.db.entities.hw.OperationalStatus;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

public class NetworkInterface extends Element {

    @Property
    private String name;

    @Property
    private OperationalStatus operationalStatus;

    public String getName() {
        return name;
    }

    public NetworkInterface setName(String name) {
        this.name = name;
        return this;
    }

    public OperationalStatus getOperationalStatus() {
        return operationalStatus;
    }

    public NetworkInterface setOperationalStatus(OperationalStatus operationalStatus) {
        this.operationalStatus = operationalStatus;
        return this;
    }
}
