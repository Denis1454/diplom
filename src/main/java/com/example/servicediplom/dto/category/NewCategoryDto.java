package com.example.servicediplom.dto.category;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewCategoryDto {
    @Size(min = 1,max = 50)
    String name;
}
