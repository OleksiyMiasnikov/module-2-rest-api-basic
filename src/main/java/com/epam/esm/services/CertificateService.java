package com.epam.esm.services;

import com.epam.esm.models.Certificate;
import com.epam.esm.repositories.CertificateRepository;
import com.epam.esm.util.CertificateValidator;
import com.epam.esm.util.ModuleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CertificateService {

    private final CertificateRepository repo;
    private final CertificateValidator validator;
    public Certificate create(Certificate certificate) {
        log.info("Service. Create certificate with name: " + certificate.getName());

        final DataBinder dataBinder = new DataBinder(certificate);
        dataBinder.addValidators(validator);
        dataBinder.validate();
        if (dataBinder.getBindingResult().hasErrors()){
            log.error("Validation error");
            String allErrors = dataBinder.getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining("; "));
            String allCodes = dataBinder.getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getCode)
                    .collect(Collectors.joining("; "));

            throw new ModuleException(allErrors, allCodes);
        }
        int result = repo.create(certificate);
        return findById(result);
    }

    public List<Certificate> findAll() {
        log.info("Service. Find all certificates");
        return repo.findAll();
    }

    public Certificate findById(int id) {
        log.info("Service. Find certificate by id: " + id);
        return repo.findById(id)
                .orElseThrow(() -> new ModuleException("Requested certificate is not found (id=" + id + ")", "40411"));
    }

    public Certificate update(int id, Certificate certificate) {
        log.info("Service. Update certificate by id: " + id);
        Certificate oldCertificate = findById(id);
        if (certificate.getName() == null) {
            certificate.setName(oldCertificate.getName());
        }
        if (certificate.getDescription() == null) {
            certificate.setDescription(oldCertificate.getDescription());
        }
        if (certificate.getPrice() == null) {
            certificate.setPrice(oldCertificate.getPrice());
        }
        if (certificate.getDuration() == null) {
            certificate.setDuration(oldCertificate.getDuration());
        }
        repo.update(id, certificate);
        return findById(id);
    }

    public boolean delete(int id) {
        log.info("Service. Delete certificate by id: " + id);
        return repo.delete(id);
    }

}
