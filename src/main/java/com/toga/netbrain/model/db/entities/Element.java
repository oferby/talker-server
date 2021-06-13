package com.toga.netbrain.model.db.entities;

import org.springframework.data.neo4j.core.schema.*;

public abstract class Element {

    @Id @GeneratedValue
    protected Long id;

    @Property
    protected String name;

    @Relationship("ROOT_ELEMENT")
    private Element rootElement;

    public Element() {
    }

    public Element(String name) {
        this.name = name;
    }

    public Element(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Element getRootElement() {
        return rootElement;
    }

    public void setRootElement(Element rootElement) {
        this.rootElement = rootElement;
    }

    @Override
    public String toString() {
        return "Element{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
