package com.toga.talker.model.db.entities.sw;

import org.springframework.data.neo4j.core.schema.Property;

public class Process extends Software {

    @Property
    private Long processId;

    public Long getProcessId() {
        return processId;
    }

    public Process setProcessId(Long processId) {
        this.processId = processId;
        return this;
    }
}
