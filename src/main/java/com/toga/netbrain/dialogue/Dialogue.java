package com.toga.netbrain.dialogue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dialogue {

    private String text;
    private String imageURL;
    private List<DialogueOption>dialogueOptionList;
    private List<String> history = new ArrayList<>();
    private Map<String, String> context = new HashMap<>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        history.add(this.text);
        history.add(text);
        this.text = text;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<DialogueOption> getDialogueOptionList() {
        return dialogueOptionList;
    }

    public void setDialogueOptionList(List<DialogueOption> dialogueOptionList) {
        this.dialogueOptionList = dialogueOptionList;
    }

    public void addDialogueOption(String text, String responseURI) {
        if (dialogueOptionList == null)
            dialogueOptionList = new ArrayList<>();
        dialogueOptionList.add(new DialogueOption(text, responseURI));

    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

    public Map<String, String> getContext() {
        return context;
    }

    public void setContext(Map<String, String> context) {
        this.context = context;
    }
}
