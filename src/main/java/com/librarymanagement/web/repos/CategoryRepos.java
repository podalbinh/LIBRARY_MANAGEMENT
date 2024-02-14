package com.librarymanagement.web.repos;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.librarymanagement.web.entities.Category;

@Repository
public interface CategoryRepos extends JpaRepository<Category, Long> {
        Set<Category> findByIdIn(Set<Long> Id);

}
