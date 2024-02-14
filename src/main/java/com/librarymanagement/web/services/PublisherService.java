package com.librarymanagement.web.services;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.librarymanagement.web.entities.Book;
import com.librarymanagement.web.entities.Category;
import com.librarymanagement.web.entities.Publisher;
import com.librarymanagement.web.exception.NotFoundException;
import com.librarymanagement.web.models.PublisherDTO;
import com.librarymanagement.web.repos.BookRepos;
import com.librarymanagement.web.repos.PublisherRepos;
import com.librarymanagement.web.repos.PublisherRepos;

@Service
public class PublisherService {
    public PublisherService(PublisherRepos publisherRepos, BookRepos bookRepos) {
        this.publisherRepos = publisherRepos;
        this.bookRepos = bookRepos;
    }

    private PublisherRepos publisherRepos;
    private BookRepos bookRepos;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Publisher findPublisherById(final Long id) {
        return publisherRepos.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("publisher not found with ID %d", id)));
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Publisher> findAllPublisher() {
        return publisherRepos.findAll();
    }

    public Publisher mapToEntity(PublisherDTO publisherDTO, Publisher publisher) {
        publisher = modelMapper.map(publisherDTO, Publisher.class);
        return publisher;
    }

    public void delete(final Long id) {
        Publisher publisher = findPublisherById(id);
        if (publisher.getBooks() != null) {
            for (Book book : publisher.getBooks()) {
                book.getPublishers().remove(publisher);
                bookRepos.save(book);
            }
        }
        publisherRepos.deleteById(publisher.getId());
    }

    public void update(final Long id, final PublisherDTO publisherDTO) {
        Publisher publisher = findPublisherById(id);
        publisher = mapToEntity(publisherDTO, new Publisher());
        publisherRepos.save(publisher);
    }

    public Long create(final PublisherDTO publisherDTO) {
        Publisher publisher = mapToEntity(publisherDTO, new Publisher());
        return publisherRepos.save(publisher).getId();
    }
}
