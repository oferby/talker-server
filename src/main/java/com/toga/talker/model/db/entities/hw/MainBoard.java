package com.toga.talker.model.db.entities.hw;

import org.springframework.data.neo4j.core.schema.Property;

public class MainBoard extends Hardware {

    @Property
    private CPU cpu;

    @Property
    private Memory memory;

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }
}
