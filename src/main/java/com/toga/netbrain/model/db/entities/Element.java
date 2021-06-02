package com.toga.netbrain.model.db.entities;

import org.springframework.data.neo4j.core.schema.*;

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
