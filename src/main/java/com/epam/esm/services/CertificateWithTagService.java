package com.epam.esm.services;

import com.epam.esm.models.Certificate;
import com.epam.esm.models.CertificateWithTag;
import com.epam.esm.repositories.CertificateRepository;
import com.epam.esm.repositories.CertificateWithTagRepository;
import com.epam.esm.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CertificateWithTagService {
    private final CertificateWithTagRepository repo;
    private final TagRepository tagRepo;
    private final CertificateRepository certificateRepo;
    private final TagService tagService;

    public CertificateWithTag create(CertificateWithTag certificateWithTag) {
        log.info("Service. Create certificate with tag and name: " + certificateWithTag.getName());
        int tagId = 0;
        if (!certificateWithTag.getTag().isEmpty()) {
            tagId = tagRepo.findByName(certificateWithTag.getTag())
                        .stream()
                        .findAny()
                        .orElse(tagService.create(certificateWithTag.getTag()))
                    .getId();
        }
        int certificateId = certificateRepo.create(Certificate.mapper(certificateWithTag));
        repo.create(tagId, certificateId);
        return repo.findByTagIdAndCertificateId(tagId, certificateId).orElse(null);
    }

    @GetMapping()
    public List<CertificateWithTag> findAll() {
        log.info("Controller. Find all certificates with tags");
        return repo.findAll();
    }

    @GetMapping("/tag/{name}")
    public List<CertificateWithTag> showByTagName(String name) {
        log.info("Controller. Find all certificates with tag: " + name);
        return repo.showByTagName(name);
    }
}
