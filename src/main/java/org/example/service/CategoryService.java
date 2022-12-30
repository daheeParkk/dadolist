package org.example.service;

import org.example.domain.Category;
import org.example.dto.category.RequestCategory;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    List<Category> getCategory();

    Category updateCategory(Long id, RequestCategory requestCategory);

    List<Category> deleteCategory(Long id);
}
