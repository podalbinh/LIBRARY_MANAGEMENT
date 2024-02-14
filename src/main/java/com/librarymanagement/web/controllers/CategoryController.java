package com.librarymanagement.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.web.entities.Category;
import com.librarymanagement.web.models.CategoryDTO;
import com.librarymanagement.web.services.CategoryService;
@RestController
@RequestMapping(value = "/api/v1/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {
     @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategorys(){
        return ResponseEntity.ok(categoryService.findAllCategory());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody final CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.create(categoryDTO));
    }
     @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody final CategoryDTO categoryDTO) {
        categoryService.update(id, categoryDTO);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable final Long id) {
        return ResponseEntity.ok(categoryService.findCategoryById(id));
    }
}
