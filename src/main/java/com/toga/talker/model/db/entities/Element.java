package com.toga.talker.model.db.entities;

import org.springframework.data.neo4j.core.schema.*;

@Node
public abstract class Element {

    @Id @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
