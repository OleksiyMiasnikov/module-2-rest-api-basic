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
    public String newTag(@ModelAttribute("tag") @Valid Tag tag, BindingResult bindingResult) {
        log.info("new tag");
        return "tags/new";
    }

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
