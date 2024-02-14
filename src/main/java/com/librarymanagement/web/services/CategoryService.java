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
import com.librarymanagement.web.exception.NotFoundException;
import com.librarymanagement.web.models.BookDTO;
import com.librarymanagement.web.models.CategoryDTO;
import com.librarymanagement.web.repos.BookRepos;
import com.librarymanagement.web.repos.CategoryRepos;

@Service
public class CategoryService {

    public CategoryService(CategoryRepos categoryRepos, BookRepos bookRepos) {
        this.categoryRepos = categoryRepos;
        this.bookRepos = bookRepos;
    }

    private CategoryRepos categoryRepos;

    private BookRepos bookRepos;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Category findCategoryById(final Long id) {
        return categoryRepos.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category not found with ID %d", id)));
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Category> findAllCategory() {
        return categoryRepos.findAll();
    }

    public Category mapToEntity(CategoryDTO categoryDTO, Category category) {
        category = modelMapper.map(categoryDTO, Category.class);
        return category;
    }

    public void delete(final Long id) {
        Category category = findCategoryById(id);
        if (category.getBooks() != null) {
            for (Book book : category.getBooks()) {
                book.getCategories().remove(category);
                bookRepos.save(book);
            }
        }
        categoryRepos.deleteById(category.getId());
    }

    public void update(final Long id, final CategoryDTO categoryDTO) {
        Category category = findCategoryById(id);
        category = mapToEntity(categoryDTO, new Category());
        categoryRepos.save(category);
    }

    public Long create(final CategoryDTO categoryDTO) {
        Category category = mapToEntity(categoryDTO, new Category());
        return categoryRepos.save(category).getId();
    }
}
