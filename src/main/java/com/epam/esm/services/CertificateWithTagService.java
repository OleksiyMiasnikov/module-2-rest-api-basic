package com.epam.esm.services;

import com.epam.esm.models.Certificate;
import com.epam.esm.models.CertificateWithTag;
import com.epam.esm.models.Tag;
import com.epam.esm.repositories.CertificateRepository;
import com.epam.esm.repositories.CertificateWithTagRepository;
import com.epam.esm.repositories.TagRepository;
import com.epam.esm.util.CertificateWithTagValidator;
import com.epam.esm.util.ModuleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CertificateWithTagService{
    private final CertificateWithTagRepository repo;
    private final TagRepository tagRepo;
    private final CertificateRepository certificateRepo;
    private final CertificateWithTagValidator validator;

    public CertificateWithTag create(CertificateWithTag certificateWithTag) {
        log.info("Service. Create certificate with tag and name: " + certificateWithTag.getName());
        validator.validate(certificateWithTag);

        int tagId = 0;
        List<Tag> tagList = tagRepo.findByName(certificateWithTag.getTag());
        if (tagList.size() == 0) {
            tagId = tagRepo.create(certificateWithTag.getTag());
        } else {
            tagId = tagList.get(0).getId();
        }

        int certificateId = certificateRepo.create(Certificate.mapper(certificateWithTag));
        repo.create(tagId, certificateId);
        return repo.findByTagIdAndCertificateId(tagId, certificateId).orElse(null);
    }

    public List<CertificateWithTag> findAll() {
        log.info("Controller. Find all certificates with tags");
        return repo.findAll();
    }

    public List<CertificateWithTag> findByTagName(String name) {
        log.info("Controller. Find all certificates with tag: " + name);
        return repo.findByTagName(name);
    }

    public List<CertificateWithTag> findByPartOfNameOrDescription(String pattern) {
        log.info("Controller. Find certificate by part of name or description.");
        return repo.findByPartOfNameOrDescription(pattern);
    }
}
