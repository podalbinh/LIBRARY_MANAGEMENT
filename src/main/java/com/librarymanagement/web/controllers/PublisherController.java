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

import com.librarymanagement.web.entities.Publisher;
import com.librarymanagement.web.models.PublisherDTO;
import com.librarymanagement.web.services.PublisherService;


@RestController
@RequestMapping(value = "/api/v1/publishers", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.findAllPublisher());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        publisherService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody final PublisherDTO publisherDTO) {
        return ResponseEntity.ok(publisherService.create(publisherDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody final PublisherDTO publisherDTO) {
        publisherService.update(id, publisherDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> findById(@PathVariable final Long id) {
        return ResponseEntity.ok(publisherService.findPublisherById(id));
    }
}
