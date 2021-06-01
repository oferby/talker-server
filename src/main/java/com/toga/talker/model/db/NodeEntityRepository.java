package com.toga.talker.model.db;

import com.toga.talker.model.db.entities.Element;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface NodeEntityRepository extends Neo4jRepository<Element, Long> {


}
