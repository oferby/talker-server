package com.toga.netbrain.model.db.entities.management;

import java.util.SortedSet;

public class AgentPerHost implements Comparable<AgentPerHost> {

    private HostAgent host;
    private Integer numOfAgents;

    public AgentPerHost(HostAgent host, Integer numOfAgents) {
        this.host = host;
        this.numOfAgents = numOfAgents;
    }

    public HostAgent getHost() {
        return host;
    }

    public Integer getNumOfAgents() {
        return numOfAgents;
    }

    @Override
    public int compareTo(AgentPerHost that) {
        if (this.numOfAgents > that.numOfAgents)
            return 1;
        if (this.numOfAgents < that.numOfAgents)
            return -1;
        return 0;
    }
}
