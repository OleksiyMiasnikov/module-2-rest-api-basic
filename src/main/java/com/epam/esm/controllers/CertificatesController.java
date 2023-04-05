package com.epam.esm.controllers;

import com.epam.esm.dao.CertificateDAO;
import com.epam.esm.models.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/certificates")
public class CertificatesController {

    @Autowired
    CertificateDAO certificateDAO;

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
        System.out.println("new certificate");
        model.addAttribute("certificate", Certificate.builder().build());
        return "certificates/new";
    }

    @PostMapping()
    public String create(@RequestParam("name") String name) {
        Certificate certificate = Certificate.builder().name(name).build();
        certificateDAO.create(certificate);
        return "redirect:/certificates";
    }
    /*
        @PostMapping()
        public String create(@ModelAttribute("certificate") Certificate certificate) {
            certificateDAO.create(certificate);
            return "redirect:/certificates";
        }
    
    */
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        System.out.println("to edit page");
        model.addAttribute("certificate", certificateDAO.show(id));
        return "certificates/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("certificate") Certificate certificate, @PathVariable("id") int id) {
        System.out.println("update");
        if (certificateDAO.update(id, certificate)){
            return "redirect:/certificates";
        }
        return "error";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        System.out.println("delete");
        certificateDAO.delete(id);
        return "redirect:/certificates";
    }

}
