package com.librarymanagement.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.web.entities.Author;
import com.librarymanagement.web.models.AuthorDTO;
import com.librarymanagement.web.services.AuthorService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/authors", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors(){
        return ResponseEntity.ok(authorService.findAllAuthors());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<Author> create(@RequestBody final AuthorDTO authorDTO) {
        return ResponseEntity.ok(authorService.create(authorDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable final Long id, @RequestBody final AuthorDTO authorDTO) {
        return ResponseEntity.ok(authorService.update(id, authorDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable final Long id) {
        return ResponseEntity.ok(authorService.findAuthorById(id));
    }
}
