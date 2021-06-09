package com.toga.netbrain.dialogue;

import com.toga.netbrain.agent.HostAgentManager;
import com.toga.netbrain.antlr4.NetbrainGrammarLexer;
import com.toga.netbrain.antlr4.NetbrainGrammarParser;
import com.toga.netbrain.model.db.entities.EntityExistException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class AntlrDialogueEngine implements DialogueEngine {

    private static final Logger logger = LoggerFactory.getLogger(AntlrDialogueEngine.class);

    @Autowired
    private HostAgentManager hostAgentManager;

    @Override
    public Dialogue received(Dialogue dialogue) {

        CharStream stream = CharStreams.fromString(dialogue.getText());

        NetbrainGrammarLexer lexer = new NetbrainGrammarLexer(stream);

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        NetbrainGrammarParser parser = new NetbrainGrammarParser(tokenStream);
        parser.addErrorListener(new NetbrainErrorListener());

        NetbrainGrammarParser.BnContext bnContext;

        try {
            bnContext = parser.bn();

        } catch (ParseException pe) {
            pe.printStackTrace();
            dialogue.setText(pe.getMessage());
            return dialogue;
        }

        Map<String, String> values = bnContext.values;

        logger.debug(values.toString());

        switch (values.get("operator")) {
            case "addNewHostAgent" -> {
                addHostAgent(values, dialogue);
            }
            case "addNewAgent" -> {
                addNewAgent(values, dialogue);
            }
            case "provideUsernameAndPassword" -> {
                provideUsernameAndPassword(values, dialogue);
            }

            case "shutDownHostAgent" -> {
                shutDownHostAgent(values, dialogue);
            }

            case "deleteAgent" -> {
                deleteAgent(values, dialogue);
            }

            case "" -> {
                runAgentDiscovery(values, dialogue);
            }

        }


        return dialogue;
    }

    private void runAgentDiscovery(Map<String, String> values, Dialogue dialogue) {


    }


    private void deleteAgent(Map<String, String> values, Dialogue dialogue) {

        String target = values.get("target");
        if (target == null) {
            dialogue.setText("please enter target");
            return;
        }

        hostAgentManager.deleteDeviceAgent(target);

        dialogue.setText("agent deleted.");
    }

    private void shutDownHostAgent(Map<String, String> values, Dialogue dialogue) {

        dialogue.getNewContext().put("context", "shutDownHostAgent");
        if (!values.containsKey("host")) {
            dialogue.setText("Please provide host name/IP");
            return;
        }

        String hostAgent = values.get("host");
        hostAgentManager.deleteHostAgent(hostAgent);

        dialogue.setText("host " + hostAgent + " removed");

    }


    private void addHostAgent(Map<String, String> values, Dialogue dialogue) {

        dialogue.getNewContext().put("context", "addHostAgent");

        try {
            hostAgentManager.addHostAgent(values.get("host"));
        } catch (EntityExistException e) {
            dialogue.setText("Host already exist.");
            return;
        }

        dialogue.setText("new host agent added");
    }

    private void addNewAgent(Map<String, String> values, Dialogue dialogue) {

        dialogue.getNewContext().put("context", "addNewAgent");

        if (values.get("username") != null && values.get("password") != null) {

            if (values.get("host") == null) {
                hostAgentManager.addAgent(values.get("target"),
                        values.get("username"), values.get("password"));
            } else {
                hostAgentManager.addAgent(values.get("host"), values.get("target"),
                        values.get("username"), values.get("password"));
            }

            dialogue.setText("new agent added");

        } else {

            if (values.get("host") != null)
                dialogue.getContext().put("host", values.get("host"));
            dialogue.setText("please provide username and password");

        }

        logger.debug("new agent created");
    }


    private void provideUsernameAndPassword(Map<String, String> values, Dialogue dialogue) {

        if (dialogue.getContext().containsKey("context") && dialogue.getContext().get("context").equals("addNewAgent")) {

            if (dialogue.getContext().get("host") == null) {
                hostAgentManager.addAgent(values.get("target"),
                        values.get("username"), values.get("password"));

            } else {
                hostAgentManager.addAgent(values.get("host"), values.get("target"),
                        values.get("username"), values.get("password"));
            }

            dialogue.setText("new agent added");

        }

    }


}
