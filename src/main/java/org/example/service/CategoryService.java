package org.example.service;

import org.example.domain.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    List<Category> getCategory();

    Category updateCategory(Long id, Category category);

    List<Category> deleteCategory(Long id);
}
