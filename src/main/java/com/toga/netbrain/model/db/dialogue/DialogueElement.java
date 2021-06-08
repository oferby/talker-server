package com.toga.netbrain.model.db.dialogue;

import com.toga.netbrain.model.db.entities.Element;

public class DialogueElement extends Element {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
