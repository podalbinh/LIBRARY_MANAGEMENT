package com.librarymanagement.web.repos;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.librarymanagement.web.entities.Book;

@Repository
public interface BookRepos extends JpaRepository<Book, Long> {
    Set<Book> findByIdIn(Set<Long> Id);
}
