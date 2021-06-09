package com.toga.netbrain.dialogue;

import com.toga.netbrain.antlr4.AgentInformationExtractorLexer;
import com.toga.netbrain.antlr4.AgentInformationExtractorParser;
import com.toga.netbrain.model.db.entities.Element;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.io.InputStream;

@Controller
public class AlntlrUriParserController implements URIParserController {

    private static final Logger logger = LoggerFactory.getLogger(AlntlrUriParserController.class);

    public void parseURI(String uri) {

        CharStream stream = CharStreams.fromString(uri);
        AgentInformationExtractorLexer lexer = new AgentInformationExtractorLexer(stream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        AgentInformationExtractorParser parser = new AgentInformationExtractorParser(tokenStream);

        AgentInformationExtractorParser.ExtractorContext context;

        try {
            context = parser.extractor();
            Element rootElement = context.rootElement;
            System.out.println(rootElement.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
