package com.toga.talker.model.db.entities.sw;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node({"Process","Element"})
public class Process extends Software {

    @Property
    private Long processId;

    @Property
    private String name;

    public Long getProcessId() {
        return processId;
    }

    public Process setProcessId(Long processId) {
        this.processId = processId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Process setName(String name) {
        this.name = name;
        return this;
    }
}
