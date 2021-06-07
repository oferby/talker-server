package com.toga.netbrain.dialogue;

import com.toga.netbrain.agent.AgentHostManager;
import com.toga.netbrain.antlr4.NetbrainGrammarLexer;
import com.toga.netbrain.antlr4.NetbrainGrammarParser;
import com.toga.netbrain.model.db.entities.management.HostAgent;
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
    private AgentHostManager agentHostManager;

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
        }


        return dialogue;
    }

    private void addHostAgent(Map<String, String> values, Dialogue dialogue) {

        dialogue.getContext().put("context", "addHostAgent");

        agentHostManager.addHostAgent(values.get("name"));

        dialogue.setText("new agent host added");
    }

    private void addNewAgent(Map<String, String> values, Dialogue dialogue) {

        dialogue.getContext().put("context", "addNewAgent");

        if (values.get("username") != null && values.get("password") != null) {

            dialogue.setText("new agent added");

        } else {

            dialogue.setText("please provide username and password");

        }


    }


    private void provideUsernameAndPassword(Map<String, String> values, Dialogue dialogue) {

        if (dialogue.getContext().containsKey("context") && dialogue.getContext().get("context").equals("addNewAgent")) {


            dialogue.setText("new agent added");

        }

    }

}
