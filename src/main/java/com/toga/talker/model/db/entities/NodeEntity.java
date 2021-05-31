package com.toga.talker.model.db.entities;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("NodeEntity")
public class NodeEntity {

    @Id
    private Long id;

    @Property
    private String value;


}
