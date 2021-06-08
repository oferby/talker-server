package com.toga.netbrain.model.db.dialogue;

import org.springframework.data.neo4j.core.schema.Node;

@Node({"NeededInformation", "DialogueElement"})
public class NeededInformation extends DialogueElement implements Comparable<NeededInformation> {

    private int priority;
    private boolean isMandatory;
    private String questionToAsk;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
    }

    public String getQuestionToAsk() {
        return questionToAsk;
    }

    public void setQuestionToAsk(String questionToAsk) {
        this.questionToAsk = questionToAsk;
    }

    @Override
    public int compareTo(NeededInformation other) {
        return Integer.compare(this.priority, other.priority);
    }
}
