package com.epam.esm.services;

import com.epam.esm.repositories.TagRepository;
import com.epam.esm.models.Tag;
import com.epam.esm.util.ModuleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagService {

    private final TagRepository repo;

    public Tag create(String name) {
        log.info("Service. Create tag with name: " + name);
        int result = repo.create(name);
        return findById(result);
    }

    public List<Tag> findAll() {
        log.info("Service. Find all tags");
        return repo.findAll();
    }

    public Tag findById(int id) {
        log.info("Service. Find tag by id: " + id);
        Optional<Tag> result = repo.findById(id);
        return result.orElseThrow(() -> new ModuleException("Requested tag is not found (id=" + id + ")", 40401));
    }

    public List<Tag> findByName(String name) {
        log.info("Service. Find tag by name: " + name);
        return repo.findByName(name);
    }

    public boolean delete(int id) {
        log.info("Service. Delete tag by id: " + id);
        return repo.delete(id);
    }

}
