package com.toga.netbrain.model.db.entities.sw;

import com.toga.netbrain.model.db.entities.Element;
import com.toga.netbrain.model.db.entities.org.Vendor;
import org.springframework.data.neo4j.core.schema.Property;

public class Software extends Element {

    @Property
    private Vendor vendor;

    @Property
    private String version;

    public String getVersion() {
        return version;
    }

    public Software setVersion(String version) {
        this.version = version;
        return this;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public Software setVendor(Vendor vendor) {
        this.vendor = vendor;
        return this;
    }

}
