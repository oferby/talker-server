package com.toga.netbrain.dialogue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dialogue {

    private String text;
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


    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

    public Map<String, String> getContext() {
        return context;
    }

    public Map<String, String> getNewContext() {
        this.context = new HashMap<>();
        return this.context;
    }

    public void setContext(Map<String, String> context) {
        this.context = context;
    }
}
