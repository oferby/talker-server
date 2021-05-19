package com.toga.talker.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IsAliveWebController {

    @GetMapping("/isAlive")
    public String isAlive(){
        return "True";
    }

}
