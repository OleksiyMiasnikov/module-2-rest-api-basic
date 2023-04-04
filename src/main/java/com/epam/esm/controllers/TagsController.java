package com.epam.esm.controllers;

import com.epam.esm.dao.TagDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
