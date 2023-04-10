package com.epam.esm.controllers;

import com.epam.esm.repositories.CertificateWithTagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/certificates_with_tags")
@RequiredArgsConstructor
public class CertificatesWithTagsController {

    private final CertificateWithTagRepository dao;

    @GetMapping()
    public String index(Model model) {
        log.info("index loaded");
        model.addAttribute("certificates_with_tags", dao.index());
        return "certificates_with_tags/index";
    }

    @GetMapping("/tag/{name}")
    public String showByTagId(@PathVariable("name") String name,
                       Model model) {
        log.info("showByTagId loaded");
        model.addAttribute("certificates_with_tags", dao.showByTagName(name));
        return "certificates_with_tags/index";
    }
}
