package com.toga.talker.model.db.entities.hw.inf;

import com.toga.talker.model.db.entities.RelationshipPropertyElement;
import com.toga.talker.model.db.entities.hw.LineCard;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.List;

@RelationshipProperties
public class LineCardNumberProperty extends RelationshipPropertyElement {

    @Property
    private String lineCardId;

    @TargetNode
    private LineCard lineCard;

    public String getLineCardId() {
        return lineCardId;
    }

    public LineCardNumberProperty setLineCardId(String lineCardId) {
        this.lineCardId = lineCardId;
        return this;
    }

    public LineCard getLineCard() {
        return lineCard;
    }

    public LineCardNumberProperty setLineCard(LineCard lineCard) {
        this.lineCard = lineCard;
        return this;
    }
}
