package com.example.servicediplom.pub.category;

import com.example.servicediplom.dto.category.CategoryDto;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
public class PublicCategoryController {

    private final PublicCategoryService publicCategoryService;

    @GetMapping()
    public List<CategoryDto> getCategorys(@RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
                                          @RequestParam(required = false, defaultValue = "10") @Positive Integer size) {
        return publicCategoryService.getCategorys(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto getCategoryById(@PathVariable Long catId) {
        return publicCategoryService.getCategoryById(catId);
    }


}
