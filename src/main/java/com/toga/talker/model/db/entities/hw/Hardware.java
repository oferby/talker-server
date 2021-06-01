package com.toga.talker.model.db.entities.hw;

import com.toga.talker.model.db.entities.Element;
import com.toga.talker.model.db.entities.org.Vendor;
import com.toga.talker.model.db.entities.sw.Software;
import org.springframework.data.neo4j.core.schema.Property;

public class Hardware extends Element {

    @Property
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
