package com.toga.talker;

import com.toga.talker.model.db.NodeEntityRepository;
import com.toga.talker.model.db.entities.DataSizeUnits;
import com.toga.talker.model.db.entities.Element;
import com.toga.talker.model.db.entities.hw.*;
import com.toga.talker.model.db.entities.hw.inf.EthernetPort;
import com.toga.talker.model.db.entities.org.Vendor;
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

        assert !all.isEmpty();

    }

    @Test
    public void deleteAll() {
        nodeEntityRepository.deleteAll();

        List<Element> all = nodeEntityRepository.findAll();

        assert all.isEmpty();

    }

    @Test
    public void testAddNode() {

        Host host = new Host();
        CPU cpu = new CPU();
        cpu.setVendor(new Vendor().setName("Intel"));
        host.setCpu(cpu);
        host.setMemory(new Memory().setAmount(1).setDataSizeUnits(DataSizeUnits.GB));

        Host host1 = nodeEntityRepository.save(host);
        Long id = host1.getId();

        assert id != null;

        Optional<Element> hostById = nodeEntityRepository.findById(id);

        assert hostById.isPresent();

        nodeEntityRepository.deleteById(id);

        hostById = nodeEntityRepository.findById(id);

        assert !hostById.isPresent();

    }

    @Test
    public void testBuildChassis() {

        Chassis chassis = new Chassis().setVendor(new Vendor().setName("Cisco"));

        for (int i = 0; i < 5; i++) {

            LineCard lineCard = new LineCard();

            for (int j = 0; j < 4; j++) {

                lineCard.addNetworkInterface(new EthernetPort()
                        .setOperationalStatus(OperationalStatus.UP)
                        .setName("eth" + j)
                );

            }

            chassis.addLineCard(lineCard, "lc-" + i);

        }


        Chassis save = nodeEntityRepository.save(chassis);
        Long id = save.getId();

        Optional<Element> optional = nodeEntityRepository.findById(id);

        assert optional.isPresent();

//        nodeEntityRepository.deleteById(id);
//
//        optional = nodeEntityRepository.findById(id);
//
//        assert !optional.isPresent();

    }


}
