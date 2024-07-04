package com.example.servicediplom.admin.category;

import com.example.servicediplom.dto.category.CategoryDto;
import com.example.servicediplom.dto.category.NewCategoryDto;

public interface AdminCategoryService {
    CategoryDto createCategory(NewCategoryDto newCategoryDto);

    void deleteCategory(Long catId);

    CategoryDto updateCategory(Long catId,NewCategoryDto newCategoryDto);
}
