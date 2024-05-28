package com.example.servicediplom.compilation.dto;

import com.example.servicediplom.event.dto.EventShortDto;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CompilationDto {
    EventShortDto events;

    int id;

    boolean pinned;

    String title;
}
