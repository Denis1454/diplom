package com.example.servicediplom.pub.category;

import com.example.servicediplom.dto.category.CategoryDto;

import java.util.List;

public interface PublicCategoryService {

    List<CategoryDto> getCategorys(Integer from, Integer size);

    CategoryDto getCategoryById(Long catId);
}
