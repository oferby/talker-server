package com.toga.talker.model.db.entities.hw;

import com.toga.talker.model.db.entities.sw.Process;
import com.toga.talker.model.db.entities.sw.Software;
import org.springframework.data.neo4j.core.schema.*;

@Node
public class Host extends NetworkElement {

    @Property
    private CPU cpu;

    @Property
    private Memory memory;

    @Relationship("RUNS")
    private Process process;

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
