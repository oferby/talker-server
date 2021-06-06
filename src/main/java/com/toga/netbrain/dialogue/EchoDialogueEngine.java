package com.toga.netbrain.dialogue;

import org.springframework.stereotype.Controller;

//@Controller
public class EchoDialogueEngine implements DialogueEngine {

    @Override
    public Dialogue received(Dialogue dialogue) {
        dialogue.setText("you said: " + dialogue.getText());
        return dialogue;
    }


}
