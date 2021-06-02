package com.toga.netbrain.model.db;

import com.toga.netbrain.model.db.entities.Element;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface NodeEntityRepository extends Neo4jRepository<Element, Long> {


}
