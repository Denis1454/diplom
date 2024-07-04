package com.example.servicediplom.pub.category;

import com.example.servicediplom.dto.category.CategoryDto;
import com.example.servicediplom.entities.Category;
import com.example.servicediplom.exception.NotFoundException;
import com.example.servicediplom.mapper.CategoryMapperDto;
import com.example.servicediplom.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicCategoryServiceImpl implements PublicCategoryService {
    private static final Logger log = LoggerFactory.getLogger(PublicCategoryServiceImpl.class);
    private final String ERR_MESSAGE_CATEGORY_NOT_FOUND = "Категории с id=%s не существует";
    private final CategoryRepository categoryRepository;
    private final CategoryMapperDto categoryMapperDto;

    @Override
    public List<CategoryDto> getCategories(Integer from, Integer size) {
        log.info("Начало операции getCategories");
        Pageable pageable = PageRequest.of(from, size);
        Page<Category> categories = categoryRepository.findAll(pageable);
        List<CategoryDto> categoryDto = categoryMapperDto.toListDto(categories);
        log.info("Конец операции getCategories" + categoryDto);
        return categoryDto;
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        log.info(String.format("Начало операции getCategoryById, categoryId = %s ", categoryId));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(String.format(ERR_MESSAGE_CATEGORY_NOT_FOUND, categoryId)));
        CategoryDto categoryDto = categoryMapperDto.toDto(category);
        log.info("Конец операции getCategoryById" + categoryDto);
        return categoryDto;
    }
}
