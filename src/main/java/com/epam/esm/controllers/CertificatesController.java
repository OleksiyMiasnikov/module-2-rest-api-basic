package com.epam.esm.controllers;

import com.epam.esm.dao.CertificateDAO;
import com.epam.esm.models.Certificate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Slf4j
@Controller
@RequestMapping("/certificates")
@RequiredArgsConstructor
public class CertificatesController {

    private final CertificateDAO certificateDAO;
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("certificates", certificateDAO.index());
        return "certificates/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("certificate", certificateDAO.show(id));
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
        certificateDAO.create(certificate);
        return "redirect:/certificates";
    }
    
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        log.info("to edit page");
        model.addAttribute("certificate", certificateDAO.show(id));
        return "certificates/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("certificate") Certificate certificate, @PathVariable("id") int id) {
        log.info("update");
        if (certificateDAO.update(id, certificate)){
            return "redirect:/certificates";
        }
        return "error";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        log.info("delete");
        certificateDAO.delete(id);
        return "redirect:/certificates";
    }

}
