package org.example.controller;

import org.example.annotation.NoAuth;
import org.example.annotation.Permission;
import org.example.domain.Category;
import org.example.dto.category.RequestCategory;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){

        this.categoryService = categoryService;

    }

    @Permission(role = Permission.PermissionRole.ADMIN)
    @PostMapping
    public Category createCategory(@RequestBody Category category){

        return categoryService.createCategory(category);

    }

    @NoAuth
    @GetMapping
    public List<Category> getCategory(){

        return categoryService.getCategory();

    }

    @Permission(role = Permission.PermissionRole.ADMIN)
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable("id") Long id, @RequestBody RequestCategory requestCategory){

        return categoryService.updateCategory(id, requestCategory);

    }

    @Permission(role = Permission.PermissionRole.ADMIN)
    @DeleteMapping("/{id}")
    public List<Category> deleteCategory(@PathVariable("id") Long id){

        return categoryService.deleteCategory(id);

    }

}
