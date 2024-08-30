package com.example.servicediplom.dto.event;

import com.example.servicediplom.entities.Location;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateEventUserRequest {
    @Size(min = 20, max = 2000)
    String annotation;

    int category;
    @Size(min = 20, max = 7000)
    String description;

    String eventDate;

    Location location;

    boolean paid;

    int participantLimit;

    boolean requestModeration;

    String stateAction;
    @Size(min = 3, max = 120)
    String title;
}
