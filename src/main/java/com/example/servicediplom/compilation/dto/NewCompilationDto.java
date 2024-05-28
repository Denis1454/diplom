package com.example.servicediplom.compilation.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class NewCompilationDto {
    List<Integer> events;

    boolean pinned;

    String title;
}
