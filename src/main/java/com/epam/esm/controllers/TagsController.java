package com.epam.esm.controllers;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.exception.TagException;
import com.epam.esm.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tags")
public class TagsController {

    @Autowired
    TagDAO tagDAO;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("tags", tagDAO.index());
        return "tags/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("tag", tagDAO.show(id));
        return "tags/show";
    }

    @GetMapping("/new")
    public String newTag(Model model) {
        model.addAttribute("new_tag", Tag.builder().build());
        return "tags/new";
    }

    @PostMapping()
    public String create(@RequestParam("name") String name) {
        Tag tag = Tag.builder().name(name).build();
        tagDAO.create(tag);
        return "redirect:/tags";
    }
/*
    @PostMapping()
    public String create(@ModelAttribute("new_tag") Tag tag) {
        tagDAO.create(tag);
        return "redirect:/tags";
    }
*/
}
