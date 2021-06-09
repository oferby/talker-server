package com.toga.netbrain;

import com.toga.netbrain.dialogue.URIParserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestParser {

    @Autowired
    private URIParserController uriParserController;

    @Test
    public void testParseURI() {

        uriParserController.parseURI("\\host\\h1\\agent\\a1");


    }

}
