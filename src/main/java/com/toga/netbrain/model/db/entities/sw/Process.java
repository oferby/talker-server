package com.toga.netbrain.model.db.entities.sw;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node({"Process","Element"})
public class Process extends Software {

    private Long processId;

    public Process() {
    }

    public Process(Long processId, String name) {
        this.processId = processId;
        this.setName(name);

    }

    public Long getProcessId() {
        return processId;
    }

    public Process setProcessId(Long processId) {
        this.processId = processId;
        return this;
    }


}
