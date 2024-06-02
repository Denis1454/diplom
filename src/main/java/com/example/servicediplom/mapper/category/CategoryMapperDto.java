package com.example.servicediplom.mapper.category;

import com.example.servicediplom.entities.Category;
import com.example.servicediplom.dto.category.CategoryDto;
import com.example.servicediplom.dto.category.NewCategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapperDto {
    CategoryDto toDto(Category category);

    Category toCategory(CategoryDto categoryDto);

    NewCategoryDto toNewCategoryDto(Category category);

    Category toNewCategory(NewCategoryDto newCategoryDto);
}
