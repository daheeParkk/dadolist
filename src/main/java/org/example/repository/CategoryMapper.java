package org.example.repository;

import org.apache.ibatis.annotations.Param;
import org.example.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {

    void createCategory(Category category);

    Category selectCategory(Long id);

    List<Category> getCategory();

    void updateCategory(Category category);

    void softDelete(@Param("isDeleted") Boolean isDeleted, @Param("id") Long id);

}
