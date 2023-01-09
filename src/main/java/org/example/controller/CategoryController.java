package org.example.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.example.annotation.NoAuth;
import org.example.annotation.Permission;
import org.example.domain.Category;
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
    public CategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @ApiOperation(value = "카테고리 생성", notes = "관리자만 가능")
    @Permission(role = Permission.PermissionRole.ADMIN)
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {

        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

    @ApiOperation(value = "카테고리 리스트 조회")
    @NoAuth
    @GetMapping
    public ResponseEntity<List<Category>> getCategory() {

        return new ResponseEntity<>(categoryService.getCategory(), HttpStatus.OK);
    }

    @ApiOperation(value = "카테고리 수정", notes = "관리자만 가능")
    @ApiImplicitParam(name = "id", value = "카테고리 식별 아이디")
    @Permission(role = Permission.PermissionRole.ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category) {

        return new ResponseEntity<>(categoryService.updateCategory(id, category), HttpStatus.OK);
    }

    @ApiOperation(value = "카테고리 삭제", notes = "관리자만 가능")
    @ApiImplicitParam(name = "id", value = "카테고리 식별 아이디")
    @Permission(role = Permission.PermissionRole.ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Category>> deleteCategory(@PathVariable("id") Long id) {

        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.NO_CONTENT);
    }

}
