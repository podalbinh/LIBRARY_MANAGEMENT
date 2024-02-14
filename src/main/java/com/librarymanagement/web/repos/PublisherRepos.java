package com.librarymanagement.web.repos;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.librarymanagement.web.entities.Publisher;

@Repository
public interface PublisherRepos extends JpaRepository<Publisher,Long>{
    Set<Publisher>  findByIdIn(Set<Long> Id);
}
