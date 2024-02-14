package com.librarymanagement.web.services;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.librarymanagement.web.entities.Author;
import com.librarymanagement.web.entities.Book;
import com.librarymanagement.web.entities.Category;
import com.librarymanagement.web.entities.Publisher;
import com.librarymanagement.web.exception.NotFoundException;
import com.librarymanagement.web.models.BookDTO;
import com.librarymanagement.web.repos.AuthorRepos;
import com.librarymanagement.web.repos.BookRepos;
import com.librarymanagement.web.repos.CategoryRepos;
import com.librarymanagement.web.repos.PublisherRepos;

@Service
public class BookService {
    public BookService(BookRepos bookRepos, CategoryRepos categoryRepos, PublisherRepos publisherRepos,
            AuthorRepos authorRepos) {
        this.bookRepos = bookRepos;
        this.categoryRepos = categoryRepos;
        this.publisherRepos = publisherRepos;
        this.authorRepos = authorRepos;
    }

    @Autowired
    ModelMapper modelMapper;
    private BookRepos bookRepos;
    private CategoryRepos categoryRepos;
    private PublisherRepos publisherRepos;
    private AuthorRepos authorRepos;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Book findBookById(final Long id) {
        return bookRepos.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Book> findAllBooks() {
        return bookRepos.findAll();
    }

    public Book mapToEntity(BookDTO bookDTO, Book book) {
        book = modelMapper.map(bookDTO, Book.class);
        Set<Author> setAuthors = authorRepos.findByIdIn(bookDTO.getAuthors());
        Set<Publisher> setPublishers = publisherRepos.findByIdIn(bookDTO.getPublishers());
        Set<Category> setCategories = categoryRepos.findByIdIn(bookDTO.getCategories());
        if (bookDTO.getAuthors() != null && (book.getAuthors() == null || !book.getAuthors().equals(setAuthors))) {
            book.setAuthors(setAuthors);
        }
        if (bookDTO.getPublishers() != null
                && (book.getPublishers() == null || !book.getPublishers().equals(setPublishers))) {
            book.setPublishers(setPublishers);
        }
        if (bookDTO.getCategories() != null
                && (book.getCategories() == null || !book.getCategories().equals(setCategories))) {
            book.setCategories(setCategories);
        }
        return book;
    }

    public void update(final Long id, final BookDTO bookDTO) {
        Book book = findBookById(id);
        book = mapToEntity(bookDTO, new Book());
        bookRepos.save(book);
    }

    public void delete(final Long id) {
        bookRepos.deleteById(id);
    }

    public Long create(final BookDTO bookDTO) {
        Book book = mapToEntity(bookDTO, new Book());
        return bookRepos.save(book).getId();
    }
}
