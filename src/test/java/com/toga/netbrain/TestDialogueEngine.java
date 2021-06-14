package com.toga.netbrain;

import com.toga.netbrain.dialogue.DialogueEngine;
import com.toga.netbrain.dialogue.Dialogue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDialogueEngine {


    @Autowired
    private DialogueEngine dialogueEngine;

    private String target = "10.100.99.187";


    @Test
    public void testAddAgent() {

        Dialogue dialogue = new Dialogue();
        dialogue.setText("add agent " + target);

        dialogue = dialogueEngine.received(dialogue);

        assert dialogue.getText().equals("please provide username and password");
        assert dialogue.getContext() !=null;
        assert dialogue.getContext().containsKey("context");
        assert dialogue.getContext().get("context").equals("addNewAgent");
        assert dialogue.getContext().get("target").equals(target);

        dialogue.setText("username oferb password 123456");
        dialogue = dialogueEngine.received(dialogue);

        assert dialogue.getText().equals("new agent added");


    }




}
