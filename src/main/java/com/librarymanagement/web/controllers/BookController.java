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
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.web.entities.Book;
import com.librarymanagement.web.models.BookDTO;
import com.librarymanagement.web.services.BookService;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(value = "/api/v1/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.findAllBooks());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody final BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.create(bookDTO));
    }
     @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody final BookDTO bookDTO) {
        bookService.update(id, bookDTO);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable final Long id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }
}
