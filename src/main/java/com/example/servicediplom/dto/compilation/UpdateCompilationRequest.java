package com.example.servicediplom.dto.compilation;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class UpdateCompilationRequest {
    List<Integer> events;

    boolean pinned;
    @Size(min = 1, max = 50)
    String title;
}
