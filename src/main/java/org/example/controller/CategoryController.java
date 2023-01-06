package org.example.controller;

import org.example.annotation.NoAuth;
import org.example.annotation.Permission;
import org.example.domain.Category;
import org.example.dto.category.RequestCategory;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Category> createCategory(@RequestBody Category category){

        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);

    }

    @NoAuth
    @GetMapping
    public ResponseEntity<List<Category>> getCategory(){

        return new ResponseEntity<>(categoryService.getCategory(), HttpStatus.OK);

    }

    @Permission(role = Permission.PermissionRole.ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody RequestCategory requestCategory){

        return new ResponseEntity<>(categoryService.updateCategory(id, requestCategory), HttpStatus.OK);

    }

    @Permission(role = Permission.PermissionRole.ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Category>> deleteCategory(@PathVariable("id") Long id){

        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.NO_CONTENT);

    }

}
