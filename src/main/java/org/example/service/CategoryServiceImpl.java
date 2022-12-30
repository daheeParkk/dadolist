package org.example.service;

import org.example.domain.Category;
import org.example.dto.category.RequestCategory;
import org.example.repository.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper){

        this.categoryMapper = categoryMapper;

    }

    public Category createCategory(Category category) {

        categoryMapper.createCategory(category);
        return categoryMapper.selectCategory(category.getId());

    }

    public List<Category> getCategory() {

        return categoryMapper.getCategory();

    }

    public Category updateCategory(Long id, RequestCategory requestCategory) {

        Category category = categoryMapper.selectCategory(id);
        category.update(requestCategory);
        categoryMapper.updateCategory(category);
        return categoryMapper.selectCategory(id);

    }

    public List<Category> deleteCategory(Long id) {

        categoryMapper.softDelete(true, id);
        return categoryMapper.getCategory();

    }

}
