package com.denchev.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SystemController {

    @PostMapping(path = "/", name = "system_home")
    public String home() {
        return "";
    }
}
