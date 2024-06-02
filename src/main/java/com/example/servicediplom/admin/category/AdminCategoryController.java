package com.example.servicediplom.admin.category;

import com.example.servicediplom.dto.category.CategoryDto;
import com.example.servicediplom.dto.category.NewCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    @PostMapping()
    public CategoryDto create(@RequestBody @Validated NewCategoryDto newCategoryDto) {
        return adminCategoryService.create(newCategoryDto);
    }

    @DeleteMapping("/{catId}")
    public void delete(@PathVariable Long catId) {
        adminCategoryService.deleteCategory(catId);
    }

    @PatchMapping("/{catId}")
    public CategoryDto update(@PathVariable @Validated Long catId,
                              @RequestBody @Validated NewCategoryDto newCategoryDto) {
        return adminCategoryService.updateCategory(catId,newCategoryDto);
    }
}
