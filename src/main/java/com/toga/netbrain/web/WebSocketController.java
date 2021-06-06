package com.toga.netbrain.web;

import com.toga.netbrain.dialogue.Dialogue;
import com.toga.netbrain.dialogue.DialogueEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private DialogueEngine dialogueEngine;

    @MessageMapping("/parseDialogue")
    public void getMessage(Dialogue dialogue) {
        logger.debug("got new message: " + dialogue.getText());
        Dialogue answer = dialogueEngine.received(dialogue);
        sendResponse(answer);

    }

    private void sendResponse(Dialogue dialogueResponse) {
        template.convertAndSend("/topic/dialogue", dialogueResponse);
    }
}
