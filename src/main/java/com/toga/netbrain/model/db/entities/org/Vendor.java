package com.toga.netbrain.model.db.entities.org;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node({"Vendor","Element"})
public class Vendor extends OrganizationElement {

    public Vendor() {
    }

    public Vendor(String name) {
        super.setName(name);
    }
}
