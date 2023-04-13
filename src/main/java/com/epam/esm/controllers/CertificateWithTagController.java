package com.epam.esm.controllers;

import com.epam.esm.models.CertificateWithTag;
import com.epam.esm.services.CertificateWithTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public List<CertificateWithTag> findAll(@ModelAttribute("sort_by_name") String sortByName,
                                            @ModelAttribute("sort_by_date") String sortByDate) {
        log.info("Controller. Find all certificates with tags");
        return service.findAll(sortByName.toUpperCase(), sortByDate.toUpperCase());
    }

    @GetMapping("/tag/{name}")
    public List<CertificateWithTag> findByTagName(@PathVariable("name") String name,
                                                  @ModelAttribute("sort_by_name") String sortByName,
                                                  @ModelAttribute("sort_by_date") String sortByDate) {
        log.info("Controller. Find all certificates with tag: " + name);
        return service.findByTagName(name, sortByName.toUpperCase(), sortByDate.toUpperCase());
    }

    @GetMapping("/search/{pattern}")
    public List<CertificateWithTag> findByPartOfNameOrDescription(@PathVariable("pattern") String pattern) {
        log.info("Controller. Find certificate by part of name or description");
        return service.findByPartOfNameOrDescription(pattern);
    }
}
