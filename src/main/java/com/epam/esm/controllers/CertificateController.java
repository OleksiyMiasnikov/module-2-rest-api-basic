package com.epam.esm.controllers;

import com.epam.esm.models.Certificate;
import com.epam.esm.services.CertificateService;
import com.epam.esm.util.ModuleException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/certificates")
@RequiredArgsConstructor
public class CertificateController{

    private final CertificateService service;
    @PostMapping()
    public Certificate create(@ModelAttribute("certificate") @Valid Certificate certificate,
                              BindingResult bindingResult) {
        log.info("Controller. Create certificate with name: " + certificate.getName());
        if (bindingResult.hasErrors()) {
            throw new ModuleException(Objects.requireNonNull(bindingResult.getFieldError())
                            .getDefaultMessage(),
                    "400001");
        }
        return service.create(certificate);
    }

    @GetMapping()
    public List<Certificate> findAll() {
        log.info("Controller. Find all certificates");
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Certificate findById(@PathVariable("id") int id) {
        log.info("Controller. Find certificate by id: " + id);
        return service.findById(id);
    }

    @PatchMapping("/{id}")
    public Certificate update(@PathVariable("id") int id,
                              @ModelAttribute Certificate certificate) {
        log.info("Controller. Update certificate by id: " + id);
        return service.update(id, certificate);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        log.info("Controller. Delete certificate by id: " + id);
        return service.delete(id);
    }

}
