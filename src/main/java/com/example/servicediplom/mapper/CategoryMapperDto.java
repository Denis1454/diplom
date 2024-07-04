package com.example.servicediplom.mapper;

import com.example.servicediplom.dto.category.CategoryDto;
import com.example.servicediplom.dto.category.NewCategoryDto;
import com.example.servicediplom.entities.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapperDto {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryDto(NewCategoryDto dto, @MappingTarget Category category);
    CategoryDto toDto(Category category);
    List<CategoryDto> toListDto(Page<Category> categories);
    Category toCategory(CategoryDto categoryDto);
    NewCategoryDto toNewCategoryDto(Category category);
    Category toNewCategory(NewCategoryDto newCategoryDto);
}
