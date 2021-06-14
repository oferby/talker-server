package com.toga.netbrain.model.db.entities.sw;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class Process extends Software {

    private Long processId;

    @Relationship("HAS_PARENT")
    private Process parentPrecess;

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

    public Process getParentPrecess() {
        return parentPrecess;
    }

    public void setParentPrecess(Process parentPrecess) {
        this.parentPrecess = parentPrecess;
    }
}
