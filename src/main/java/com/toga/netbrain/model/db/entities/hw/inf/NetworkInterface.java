package com.toga.netbrain.model.db.entities.hw.inf;

import com.toga.netbrain.model.db.entities.Element;
import com.toga.netbrain.model.db.entities.hw.OperationalStatus;
import org.springframework.data.neo4j.core.schema.Property;

public class NetworkInterface extends Element {

    @Property
    private OperationalStatus operationalStatus;

    public OperationalStatus getOperationalStatus() {
        return operationalStatus;
    }

    public NetworkInterface setOperationalStatus(OperationalStatus operationalStatus) {
        this.operationalStatus = operationalStatus;
        return this;
    }
}
