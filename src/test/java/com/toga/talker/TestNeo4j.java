package com.toga.talker;

import com.toga.talker.model.db.NodeEntityRepository;
import com.toga.talker.model.db.entities.DataSizeUnits;
import com.toga.talker.model.db.entities.Element;
import com.toga.talker.model.db.entities.hw.CPU;
import com.toga.talker.model.db.entities.hw.Host;
import com.toga.talker.model.db.entities.hw.Memory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestNeo4j {

    @Autowired
    private NodeEntityRepository nodeEntityRepository;

    @Test
    public void testFindAll() {

        assert nodeEntityRepository != null;

        List<Element> all = nodeEntityRepository.findAll();

        assert  !all.isEmpty();

    }

    @Test
    public void deleteAll() {
        nodeEntityRepository.deleteAll();

        List<Element> all = nodeEntityRepository.findAll();

        assert  all.isEmpty();

    }

    @Test
    public void testAddNode() {

        Host host = new Host();
        host.setCpu(new CPU().setVendor("Intel"));
        host.setMemory(new Memory().setAmount(1).setDataSizeUnits(DataSizeUnits.GB));

        Host host1 = nodeEntityRepository.save(host);

        assert host1.getId() != null;

        Optional<Element> hostById = nodeEntityRepository.findById(host1.getId());

        assert !hostById.isEmpty();



    }


}
