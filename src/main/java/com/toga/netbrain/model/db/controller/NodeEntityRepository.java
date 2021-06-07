package com.toga.netbrain.model.db.controller;

import com.toga.netbrain.model.db.entities.Element;
import com.toga.netbrain.model.db.entities.management.AgentPerHost;
import com.toga.netbrain.model.db.entities.management.DeviceAgent;
import com.toga.netbrain.model.db.entities.management.HostAgent;
import org.neo4j.driver.internal.shaded.reactor.core.publisher.Flux;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;

public interface NodeEntityRepository extends Neo4jRepository<Element, Long> {

    Optional<HostAgent> findHostAgentByName(String name);

    DeviceAgent findDeviceAgentByTarget(String target);

    @Query("match (host:HostAgent)-[:MANAGE]->(a:DeviceAgent) return host ,count(a) as numOfAgents")
    SortedSet<AgentPerHost> findNumberOfAgentsPerHost();

    @Query("match (n:HostAgent)-[:MANAGE]->(a:DeviceAgent) where n.name=$name detach delete n,a")
    void deleteHostAgent(String name);

    @Query("match (n:HostAgent)-[:MANAGE]->(a:DeviceAgent) match (n)-[:MANAGE]->(b:DeviceAgent) where a.target=$target return n,b")
    HostAgent findHostAgentByTarget(String target);

    @Query("match (a:DeviceAgent) where a.target=$target detach delete a")
    void deleteDeviceAgentByTarget(String target);

}
