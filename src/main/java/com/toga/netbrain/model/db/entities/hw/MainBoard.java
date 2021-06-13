package com.toga.netbrain.model.db.entities.hw;

import com.toga.netbrain.model.db.entities.sw.OperatingSystem;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class MainBoard extends Hardware {

    @Relationship("HAS")
    private CPU cpu;

    @Relationship("HAS")
    private Memory memory;

    @Relationship("RUNS")
    private OperatingSystem operatingSystem;

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

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(OperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
}
