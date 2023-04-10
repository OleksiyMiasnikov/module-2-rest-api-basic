package com.epam.esm.services;

import com.epam.esm.models.Certificate;
import com.epam.esm.models.CertificateWithTag;
import com.epam.esm.models.Tag;
import com.epam.esm.repositories.CertificateRepository;
import com.epam.esm.repositories.CertificateWithTagRepository;
import com.epam.esm.repositories.TagRepository;
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

    public CertificateWithTag create(CertificateWithTag certificateWithTag) {
        log.info("Service. Create certificate with tag and name: " + certificateWithTag.getName());
        if ((certificateWithTag.getTag() == null) || (certificateWithTag.getTag().isBlank())) {
            throw new ModuleException("Field 'tag' can not be empty!", 40421);
        }
        if ((certificateWithTag.getName() == null) || (certificateWithTag.getName().isBlank())) {
            throw new ModuleException("Field 'name' can not be empty!", 40422);
        }
        if ((certificateWithTag.getDescription() == null) || (certificateWithTag.getDescription().isBlank())) {
            throw new ModuleException("Field 'description' can not be empty!", 40423);
        }
        if (certificateWithTag.getPrice() <= 0) {
            throw new ModuleException("Field 'price' should be more then 0!", 40424);
        }
        if (certificateWithTag.getDuration() <= 0) {
            throw new ModuleException("Field 'duration' should be more then 0!", 40421);
        }

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
