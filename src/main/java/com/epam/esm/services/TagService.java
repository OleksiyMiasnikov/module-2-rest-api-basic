package com.epam.esm.services;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.models.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagService {

    private final TagDAO tagDAO;

    public Tag create(String name) {
        log.info("Service. Create tag with name: " + name);
        return tagDAO.create(name);
    }

    public List<Tag> findAll() {
        log.info("Service. Find all tags");
        return tagDAO.findAll();
    }

    public Tag findById(int id) {
        log.info("Service. Find tag by id: " + id);
        return tagDAO.findById(id);
    }

    public boolean delete(int id) {
        log.info("Service. Delete tag by id: " + id);
        return tagDAO.delete(id);
    }

}
