package com.epam.esm.services;

import com.epam.esm.models.Certificate;
import com.epam.esm.repositories.CertificateRepository;
import com.epam.esm.util.ModuleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CertificateService {

    private final CertificateRepository repo;
    public Certificate create(Certificate certificate) {
        log.info("Service. Create certificate with name: " + certificate.getName());
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
                .orElseThrow(() -> new ModuleException("Requested certificate is not found (id=" + id + ")", 40411));
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
