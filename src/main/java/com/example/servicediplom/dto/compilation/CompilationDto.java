package com.example.servicediplom.dto.compilation;

import com.example.servicediplom.dto.event.EventShortDto;
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
