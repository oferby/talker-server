package com.toga.netbrain.dialogue;

public class DialogueOption {
    
    private String text;
    private String responseURI;

    public DialogueOption() {
    }

    public DialogueOption(String text, String responseURI) {
        this.text = text;
        this.responseURI = responseURI;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getResponseURI() {
        return responseURI;
    }

    public void setResponseURI(String responseURI) {
        this.responseURI = responseURI;
    }
}
