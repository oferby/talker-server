package com.toga.netbrain.model.db.entities.hw;

import com.toga.netbrain.model.db.entities.Element;
import com.toga.netbrain.model.db.entities.org.Vendor;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

public class Hardware extends Element {

    @Relationship("HAS")
    private Vendor vendor;

    @Property
    private String version;

    public String getVersion() {
        return version;
    }

    public Hardware setVersion(String version) {
        this.version = version;
        return this;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public Hardware setVendor(Vendor vendor) {
        this.vendor = vendor;
        return this;
    }

}
