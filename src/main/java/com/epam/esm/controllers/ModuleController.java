package com.epam.esm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/module")
public class ModuleController {
    @GetMapping("/hello")
    public String helloPage(){
        return "module/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage(){
        return "module/goodbye";
    }
}
