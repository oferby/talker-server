package com.toga.talker.model.db.entities.hw;

public class CPU extends Hardware {

    private String vendor;

    public String getVendor() {
        return vendor;
    }

    public CPU setVendor(String vendor) {
        this.vendor = vendor;
        return this;
    }
}
