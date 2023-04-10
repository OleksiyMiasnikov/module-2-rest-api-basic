package com.epam.esm.controllers;

import com.epam.esm.repositories.CertificateRepository;
import com.epam.esm.models.Certificate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/certificates")
@RequiredArgsConstructor
public class CertificatesController {

    private final CertificateRepository certificateRepository;
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("certificates", certificateRepository.index());
        return "certificates/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("certificate", certificateRepository.show(id));
        return "certificates/show";
    }

    @GetMapping("/new")
    public String newCertificate(Model model) {
        log.info("new certificate");
        model.addAttribute("certificate", Certificate.builder().build());
        return "certificates/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("certificate") Certificate certificate) {
        certificateRepository.create(certificate);
        return "redirect:/certificates";
    }
    
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        log.info("to edit page");
        model.addAttribute("certificate", certificateRepository.show(id));
        return "certificates/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("certificate") Certificate certificate, @PathVariable("id") int id) {
        log.info("update");
        if (certificateRepository.update(id, certificate)){
            return "redirect:/certificates";
        }
        return "error";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        log.info("delete");
        certificateRepository.delete(id);
        return "redirect:/certificates";
    }

}
