package com.epam.esm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ModuleController {
    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }
}
