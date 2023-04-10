package com.epam.esm.controllers;

import com.epam.esm.models.Tag;
import com.epam.esm.services.TagService;
import com.epam.esm.util.ModuleErrorResponse;
import com.epam.esm.util.ModuleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagsController {

    private final TagService service;

    @PostMapping()
    public Tag create(@RequestParam("name") String name) {
        log.info("Controller. Create tag with name: " + name);
        return service.create(name);
    }

    @GetMapping()
    public List<Tag> findAll() {
        log.info("Controller. Find all tags");
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Tag findById(@PathVariable("id") int id) {
        log.info("Controller. Find tag by id: " + id);
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        log.info("Controller. Delete tag by id: " + id);
        return service.delete(id);
    }
    @ExceptionHandler
    private ResponseEntity<ModuleErrorResponse> handleException(ModuleException exception){
        return new ResponseEntity<>(new ModuleErrorResponse(exception),
                HttpStatus.NOT_FOUND);
    }

}
