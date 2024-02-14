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
import com.librarymanagement.web.exception.NotFoundException;
import com.librarymanagement.web.models.AuthorDTO;
import com.librarymanagement.web.repos.AuthorRepos;
import com.librarymanagement.web.repos.BookRepos;

@Service
public class AuthorService {
    private final AuthorRepos authorRepos;
    private final BookRepos bookRepos;
    @Autowired
    private ModelMapper modelMapper;

    public AuthorService(AuthorRepos authorRepos, BookRepos bookRepos) {
        this.bookRepos = bookRepos;
        this.authorRepos = authorRepos;
    }

    private Author mapToEntity(AuthorDTO authorDTO, Author author) {
        author = modelMapper.map(authorDTO, Author.class);
        return author;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Author> findAllAuthors() {
        return authorRepos.findAll();
    }

    public void delete(final Long id) {
        final Author author = findAuthorById(id);
        if (author.getBooks() != null) {
            for (Book book : author.getBooks()) {
                book.getAuthors().remove(author);
                bookRepos.save(book);
            }
        }
        authorRepos.deleteById(author.getId());
    }

    public Long update(final Long id, final AuthorDTO authorDTO) {
        Author author = findAuthorById(id);
        author = mapToEntity(authorDTO, author);
        return authorRepos.save(author).getId();
    }

    public Author create(final AuthorDTO authorDTO) {
        Author author = mapToEntity(authorDTO, new Author());
        return authorRepos.save(author);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Author findAuthorById(final Long id) {
        return authorRepos.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author not found with ID %d", id)));
    }

}
