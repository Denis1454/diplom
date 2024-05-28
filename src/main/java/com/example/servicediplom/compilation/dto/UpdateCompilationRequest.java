package com.example.servicediplom.compilation.dto;

import com.example.servicediplom.category.Category;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class UpdateCompilationRequest {
    List<Integer> events;

    boolean pinned;

    String title;
}
