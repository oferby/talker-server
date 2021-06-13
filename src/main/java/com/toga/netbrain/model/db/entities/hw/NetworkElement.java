package com.toga.netbrain.model.db.entities.hw;

import com.toga.netbrain.model.db.entities.sw.OperatingSystem;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

public abstract class NetworkElement extends Hardware {

    @Relationship("HAS")
    private MainBoard mainBoard;

    @Relationship("HAS")
    private List<LineCard> lineCardList;

    public CPU getCpu() {
        return mainBoard.getCpu();
    }

    public void setCpu(CPU cpu) {
        if (mainBoard == null)
            mainBoard = new MainBoard();
        mainBoard.setCpu(cpu);
    }

    public Memory getMemory() {
        return mainBoard.getMemory();
    }

    public void setMemory(Memory memory) {
        if (mainBoard == null)
            mainBoard = new MainBoard();
        mainBoard.setMemory(memory);
    }


    public List<LineCard> getLineCardList() {
        return lineCardList;
    }

    public void setLineCardList(List<LineCard> lineCardList) {
        this.lineCardList = lineCardList;
    }

    public NetworkElement addLineCard(LineCard lineCard) {
        if (lineCardList == null)
            lineCardList = new ArrayList<>();
        lineCardList.add(lineCard);
        return this;
    }


}
