package com.example.servicediplom.category.mapper;

import com.example.servicediplom.category.Category;
import com.example.servicediplom.category.dto.CategoryDto;
import com.example.servicediplom.category.dto.NewCategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapperDto {
    CategoryDto toDto(Category category);

    Category toCategory(CategoryDto categoryDto);

    NewCategoryDto toNewCategoryDto(Category category);

    Category toNewCategory(NewCategoryDto newCategoryDto);
}
