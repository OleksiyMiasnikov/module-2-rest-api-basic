package com.epam.esm.controllers;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.exception.TagException;
import com.epam.esm.models.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagsController {

    private final TagDAO tagDAO;

    @GetMapping()
    public List<Tag> index(Model model) {
        return tagDAO.index();
    }

    @GetMapping("/{id}")
    public Tag show(@PathVariable("id") int id) {
        return tagDAO.show(id);
    }

    @GetMapping("/new")
    public String newTag(@ModelAttribute("tag") Tag tag) {
        log.info("new tag");
        return "tags/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("tag") Tag tag) {
        tagDAO.create(tag);
        return "redirect:/tags";
    }


    @GetMapping("/{id}/edit")
    public Tag edit(@PathVariable("id") int id) {
        return tagDAO.show(id);
    }

    @PatchMapping("/{id}")
    public boolean update(@ModelAttribute("tag") Tag tag,
                         @PathVariable("id") int id)  {
        log.info("update");
        return tagDAO.update(id, tag);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        log.info("delete");
        return tagDAO.delete(id);
    }


}
