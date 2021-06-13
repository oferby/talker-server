package com.toga.netbrain.model.db.entities.sw;


import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
public class OperatingSystem extends Software {

    @Relationship("RUNS")
    private List<Service> serviceList;

    @Relationship("RUNS")
    private List<Process> processList;

    public List<Service> getNetworkServiceList() {
        return serviceList;
    }

    public OperatingSystem setNetworkServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
        return this;
    }

    public OperatingSystem addService(Service service) {
        if (serviceList == null)
            serviceList = new ArrayList<>();

        serviceList.add(service);

        return this;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public List<Process> getProcessList() {
        return processList;
    }

    public void setProcessList(List<Process> processList) {
        this.processList = processList;
    }

    public OperatingSystem addProcess(Process process) {
        if (processList == null)
            processList = new ArrayList<>();

        processList.add(process);

        return this;
    }
}
