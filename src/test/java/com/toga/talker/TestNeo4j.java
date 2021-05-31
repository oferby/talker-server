package com.toga.talker;

import com.toga.talker.model.db.NodeEntityRepository;
import com.toga.talker.model.db.entities.Thing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestNeo4j {

    @Autowired
    private NodeEntityRepository nodeEntityRepository;

    @Test
    public void testDb() {

        assert nodeEntityRepository != null;

        List<Thing> all = nodeEntityRepository.findAll();

        assert  !all.isEmpty();

    }

    @Test
    public void deleteAll() {
        nodeEntityRepository.deleteAll();

        List<Thing> all = nodeEntityRepository.findAll();

        assert  all.isEmpty();

    }

}
