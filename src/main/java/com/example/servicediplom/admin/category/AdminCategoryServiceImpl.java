package com.example.servicediplom.admin.category;

import com.example.servicediplom.dto.category.CategoryDto;
import com.example.servicediplom.dto.category.NewCategoryDto;
import com.example.servicediplom.entities.Category;
import com.example.servicediplom.exception.CategoryHasEventsException;
import com.example.servicediplom.exception.NotFoundException;
import com.example.servicediplom.mapper.CategoryMapperDto;
import com.example.servicediplom.repository.CategoryRepository;
import com.example.servicediplom.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCategoryServiceImpl implements AdminCategoryService {
    private static final Logger log = LoggerFactory.getLogger(AdminCategoryServiceImpl.class);
    private static final String ERR_MESSAGE_CATEGORY_NOT_FOUND = "Категории с id=%s не существует";
    private static final String ERR_MESSAGE_CATEGORY_HAS_EVENT = "В Категории имеются события";
    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;
    private final CategoryMapperDto categoryMapperDto;

    @Override
    public CategoryDto createCategory(NewCategoryDto newCategoryDto) {
        log.info(String.format("Начала операции createCategory, %s ", newCategoryDto));
        Category save = categoryRepository.save(categoryMapperDto.toNewCategory(newCategoryDto));
        CategoryDto categoryDto = categoryMapperDto.toDto(save);
        log.info("Конец операции createCategory" + categoryDto);
        return categoryDto;
    }

    @Override
    public void deleteCategory(Long catId) {
        log.info(String.format("Начала операции deleteCategory, catId = %s ", catId));
        if (categoryRepository.existsById(catId)) {
            if (!eventRepository.existsEventsByCategoryId(catId)) {
                categoryRepository.deleteById(catId);
            } else {
                throw new CategoryHasEventsException(ERR_MESSAGE_CATEGORY_HAS_EVENT);
            }
        } else {
            throw new NotFoundException(String.format(ERR_MESSAGE_CATEGORY_NOT_FOUND, catId));
        }
    }

    @Override
    public CategoryDto updateCategory(Long catId, NewCategoryDto newCategoryDto) {
        log.info(String.format("Начала операции updateCategory, catId = %s, %s ", catId, newCategoryDto));
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException(String.format(ERR_MESSAGE_CATEGORY_NOT_FOUND, catId)));
        categoryMapperDto.updateCategoryDto(newCategoryDto, category);
        Category save = categoryRepository.save(category);
        CategoryDto categoryDto = categoryMapperDto.toDto(save);
        log.info("Конец операции updateCategory" + categoryDto);
        return categoryDto;
    }
}
