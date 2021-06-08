package com.toga.netbrain.model.db.dialogue;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.SortedSet;
import java.util.TreeSet;

@Node({"Context", "DialogueElement"})
public class Context extends DialogueElement {

    @Relationship
    private SortedSet<NeededInformation> neededInformationSet;

    public SortedSet<NeededInformation> getNeededInformationSet() {
        return neededInformationSet;
    }

    public void setNeededInformationSet(SortedSet<NeededInformation> neededInformationSet) {
        this.neededInformationSet = neededInformationSet;
    }

    public void addNeededInformation(NeededInformation information) {
        if (neededInformationSet == null)
            neededInformationSet = new TreeSet<>();

        neededInformationSet.add(information);
    }
}
