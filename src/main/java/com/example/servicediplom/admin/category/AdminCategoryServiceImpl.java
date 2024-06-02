package com.example.servicediplom.admin.category;

import com.example.servicediplom.entities.Category;
import com.example.servicediplom.dto.category.CategoryDto;
import com.example.servicediplom.dto.category.NewCategoryDto;
import com.example.servicediplom.mapper.category.AdminMapperDto;
import com.example.servicediplom.exception.NotFoundException;
import com.example.servicediplom.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCategoryServiceImpl implements AdminCategoryService {

    private static final String ERR_MESSAGE_CATEGORY_NOT_FOUND = "Категории с id=%s не существует";

    private final CategoryRepository categoryRepository;

    private final AdminMapperDto adminMapperDto;

    @Override
    public CategoryDto create(NewCategoryDto newCategoryDto) {
        Category save = categoryRepository.save(adminMapperDto.toNewCategory(newCategoryDto));
        return adminMapperDto.toDto(save);
    }

    @Override
    public void deleteCategory(Long catId) {//TODO с категорией не должно быть связано ни одного события(Надо сделать проверку связанно ли хоть 1 событие с категрией)
        if (categoryRepository.existsById(catId)) {
            categoryRepository.deleteById(catId);
        } else {
            throw new NotFoundException(String.format(ERR_MESSAGE_CATEGORY_NOT_FOUND, catId));
        }
    }

    @Override
    public CategoryDto updateCategory(Long catId,NewCategoryDto newCategoryDto) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException(String.format(ERR_MESSAGE_CATEGORY_NOT_FOUND, catId)));
        Category save = categoryRepository.save(category);
        return adminMapperDto.toDto(save);
    }
}
