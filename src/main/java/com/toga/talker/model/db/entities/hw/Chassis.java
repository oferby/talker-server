package com.toga.talker.model.db.entities.hw;

import com.toga.talker.model.db.entities.hw.inf.LineCardNumberProperty;
import com.toga.talker.model.db.entities.org.Vendor;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;

import java.util.ArrayList;
import java.util.List;

@Node({"Chassis", "Element"})
public class Chassis extends NetworkElement {

    @Property
    private Vendor vendor;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private List<LineCardNumberProperty> lineCardNumberProperties;

    public Vendor getVendor() {
        return vendor;
    }

    public Chassis setVendor(Vendor vendor) {
        this.vendor = vendor;
        return this;
    }

    public List<LineCardNumberProperty> getLineCardNumberProperties() {
        return lineCardNumberProperties;
    }

    public void setLineCardNumberProperties(List<LineCardNumberProperty> lineCardNumberProperties) {
        this.lineCardNumberProperties = lineCardNumberProperties;
    }

    public Chassis addLineCard(LineCard lineCard, String lineCardId) {

        if (lineCardNumberProperties == null)
            lineCardNumberProperties = new ArrayList<>();

        lineCardNumberProperties.add(new LineCardNumberProperty().setLineCard(lineCard).setLineCardId(lineCardId));

        return this;
    }
}
