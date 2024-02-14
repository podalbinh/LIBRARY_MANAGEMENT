package com.librarymanagement.web.repos;

import org.springframework.stereotype.Repository;

import com.librarymanagement.web.entities.Author;
import com.librarymanagement.web.entities.Book;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AuthorRepos  extends JpaRepository<Author,Long>{
    Set<Author> findByIdIn(Set<Long> Id);
}
