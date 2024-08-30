package com.example.servicediplom.dto.event;

import com.example.servicediplom.dto.category.CategoryDto;
import com.example.servicediplom.dto.user.UserShortDto;
import com.example.servicediplom.entities.Location;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EventFullDto {
    String annotation;

    CategoryDto category;

    int confirmedRequests;

    String createdOn;

    String description;

    String eventDate;

    int id;

    UserShortDto initiator;

    Location location;

    boolean paid;
    @PositiveOrZero
    int participantLimit;

    String publishedOn;

    boolean requestModeration;

    String state;

    String title;

    int views;
}
