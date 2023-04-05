package com.epam.esm.controllers;

import com.epam.esm.dao.TagDAO;
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
        System.out.println("new tag");
        model.addAttribute("tag", Tag.builder().build());
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
    public String create(@ModelAttribute("tag") Tag tag) {
        tagDAO.create(tag);
        return "redirect:/tags";
    }

*/
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("tag", tagDAO.show(id));
        return "tags/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("tag") Tag tag, @PathVariable("id") int id) {
        System.out.println("update");
        if (tagDAO.update(id, tag)){
            return "redirect:/tags";
        }
        return "error";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        System.out.println("delete");
        tagDAO.delete(id);
        return "redirect:/tags";
    }


}
