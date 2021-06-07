package com.toga.netbrain.model.db;

import com.toga.netbrain.model.db.controller.NodeEntityRepository;
import com.toga.netbrain.model.db.entities.EntityExistException;
import com.toga.netbrain.model.db.entities.EntityNotFoundException;
import com.toga.netbrain.model.db.entities.management.DeviceAgent;
import com.toga.netbrain.model.db.entities.management.HostAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.management.InstanceNotFoundException;

@Controller
public class EntityController {

    private static final Logger logger = LoggerFactory.getLogger(EntityController.class);

    @Autowired
    private NodeEntityRepository nodeEntityRepository;




}
