package com.toga.netbrain.model.db.entities.hw;

import org.springframework.data.neo4j.core.schema.Node;

@Node
public class CPU extends Hardware {

    private String architecture;
    private Integer numOfCPU;
    private Integer numaNodes;
    private String speed;

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public Integer getNumOfCPU() {
        return numOfCPU;
    }

    public void setNumOfCPU(Integer numOfCPU) {
        this.numOfCPU = numOfCPU;
    }

    public Integer getNumaNodes() {
        return numaNodes;
    }

    public void setNumaNodes(Integer numaNodes) {
        this.numaNodes = numaNodes;
    }
}
