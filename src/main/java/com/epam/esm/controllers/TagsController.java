package com.epam.esm.controllers;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.exception.TagException;
import com.epam.esm.models.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
    public String newTag(@ModelAttribute("tag") @Valid Tag tag, BindingResult bindingResult) {
        log.info("new tag");
        return "tags/new";
    }

//    @PostMapping()
//    public String create(@RequestParam("name") String name) {
//        Tag tag = new Tag();
//        tag.setName(name);
//        tagDAO.create(tag);
//        return "redirect:/tags";
//    }

    @PostMapping()
    public String create(@ModelAttribute("tag") @Valid Tag tag, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "tags/new";
        }
        tagDAO.create(tag);
        return "redirect:/tags";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("tag", tagDAO.show(id));
        return "tags/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("tag") @Valid Tag tag, BindingResult bindingResult,
                         @PathVariable("id") int id)  {
        log.info("update");
        if (bindingResult.hasErrors()) {
            return "tags/edit";
        }
        if (tagDAO.update(id, tag)){
            return "redirect:/tags";
        }
        return "error";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        log.info("delete");
        tagDAO.delete(id);
        return "redirect:/tags";
    }


}
