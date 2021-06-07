package com.toga.netbrain.model.db.entities.org;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node({"Vendor","Element"})
public class Vendor extends OrganizationElement {

    @Property
    private String name;

    public String getName() {
        return name;
    }

    public Vendor setName(String name) {
        this.name = name;
        return this;
    }
}
