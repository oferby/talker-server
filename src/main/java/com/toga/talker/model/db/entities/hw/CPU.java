package com.toga.talker.model.db.entities.hw;

import com.toga.talker.model.db.entities.org.Vendor;

public class CPU extends Hardware {

    private Vendor vendor;

    public Vendor getVendor() {
        return vendor;
    }

    public CPU setVendor(Vendor vendor) {
        this.vendor = vendor;
        return this;
    }
}
