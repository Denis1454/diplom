package com.example.servicediplom.event.dto;

import com.example.servicediplom.category.Category;
import com.example.servicediplom.event.enums.StateUserAction;
import com.example.servicediplom.event.Location;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
@Value
@Builder
public class UpdateEventUserRequest {
    String annotation;

    Category category;

    String description;

    LocalDateTime eventDate;

    Location location;

    boolean paid;

    int participantLimit;

    boolean requestModeration;

    StateUserAction stateAction;

    String title;
}
