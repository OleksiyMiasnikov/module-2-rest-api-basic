package com.epam.esm.controllers;

import com.epam.esm.models.CertificateWithTag;
import com.epam.esm.services.CertificateWithTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/certificates_with_tags")
@RequiredArgsConstructor
public class CertificateWithTagController{

    private final CertificateWithTagService service;

    @PostMapping()
    public CertificateWithTag create(@ModelAttribute CertificateWithTag certificateWithTag) {
        log.info("Controller. Create certificate with tag and name: "
                + certificateWithTag.getName());
        return service.create(certificateWithTag);
    }
    @GetMapping()
    public List<CertificateWithTag> findAll(Model model) {
        log.info("Controller. Find all certificates with tags");
        return service.findAll();
    }

    @GetMapping("/tag/{name}")
    public List<CertificateWithTag> showByTagName(@PathVariable("name") String name) {
        log.info("Controller. Find all certificates with tag: " + name);
        return service.findByTagName(name);
    }
}
