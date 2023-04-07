package com.epam.esm.controllers;

import com.epam.esm.dao.CertificateWithTagDAO;
import com.epam.esm.models.Certificate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/certificates_with_tags")
public class CertificatesWithTagsController {

    @Autowired
    CertificateWithTagDAO dao;

    @GetMapping()
    public String index(Model model) {
        log.info("index loaded");
        model.addAttribute("certificates_with_tags", dao.index());
        return "certificates_with_tags/index";
    }

    @GetMapping("/tag/{id}")
    public String showByTagId(@PathVariable("id") int id,
                       Model model) {
        log.info("showByTagId loaded");
        model.addAttribute("certificates_with_tags", dao.showByTagId(id));
        return "certificates_with_tags/index";
    }
}
