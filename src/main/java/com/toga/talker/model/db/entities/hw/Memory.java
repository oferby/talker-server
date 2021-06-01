package com.toga.talker.model.db.entities.hw;

import com.toga.talker.model.db.entities.DataSizeUnits;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

public class Memory extends Hardware {

    private DataSizeUnits dataSizeUnits;

    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public Memory setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public DataSizeUnits getDataSizeUnits() {
        return dataSizeUnits;
    }

    public Memory setDataSizeUnits(DataSizeUnits dataSizeUnits) {
        this.dataSizeUnits = dataSizeUnits;
        return this;
    }
}
