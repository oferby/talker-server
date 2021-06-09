package com.toga.netbrain.model.db.entities.hw;

import com.toga.netbrain.model.db.entities.sw.OperatingSystem;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;

@Node
public class Host extends NetworkElement {

    @Relationship("HAS")
    private CPU cpu;

    @Relationship("HAS")
    private Memory memory;

    @Relationship("HAS")
    private List<LineCard> lineCardList;

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

    public Host setOperatingSystem(OperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
        return this;
    }

    public List<LineCard> getLineCardList() {
        return lineCardList;
    }

    public void setLineCardList(List<LineCard> lineCardList) {
        this.lineCardList = lineCardList;
    }

    public Host addLineCard(LineCard lineCard) {
        if (lineCardList == null)
            lineCardList = new ArrayList<>();
        lineCardList.add(lineCard);
        return this;
    }
}
