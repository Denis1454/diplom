package com.example.servicediplom.mapper.category;

import com.example.servicediplom.entities.Category;
import com.example.servicediplom.dto.category.CategoryDto;
import com.example.servicediplom.dto.category.NewCategoryDto;


public interface AdminMapperDto {
    Category toNewCategory(NewCategoryDto newCategoryDto);

    CategoryDto toDto (Category category);

}
