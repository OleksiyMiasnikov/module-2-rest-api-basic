package com.epam.esm.services;

import com.epam.esm.models.Certificate;
import com.epam.esm.models.CertificateWithTag;
import com.epam.esm.models.Tag;
import com.epam.esm.repositories.CertificateRepository;
import com.epam.esm.repositories.CertificateWithTagRepository;
import com.epam.esm.repositories.TagRepository;
import com.epam.esm.util.CertificateWithTagValidator;
import com.epam.esm.util.SortingValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CertificateWithTagService{
    private final CertificateWithTagRepository repo;
    private final TagRepository tagRepo;
    private final CertificateRepository certificateRepo;
    private final CertificateWithTagValidator validator;
    private final SortingValidator sortingValidator;

    public CertificateWithTag create(CertificateWithTag certificateWithTag) {
        log.info("Service. Create certificate with tag and name: " + certificateWithTag.getName());
        validator.validate(certificateWithTag);

        int tagId;
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

    public List<CertificateWithTag> findAll(String sortByName, String sortByDate) {
        log.info("Controller. Find all certificates with tags");
        // validate sorting parameters
        sortingValidator.validate(sortByDate);
        sortingValidator.validate(sortByName);

        return repo.findAll(sortByName, sortByDate);
    }

    public List<CertificateWithTag> findByTagName(String name, String sortByName, String sortByDate) {
        log.info("Controller. Find all certificates with tag: " + name);
        // validate sorting parameters
        sortingValidator.validate(sortByDate);
        sortingValidator.validate(sortByName);

        return repo.findByTagName(name, sortByName, sortByDate);
    }

    public List<CertificateWithTag> findByPartOfNameOrDescription(String pattern) {
        log.info("Controller. Find certificate by part of name or description.");
        return repo.findByPartOfNameOrDescription(pattern);
    }
}
