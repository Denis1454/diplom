package com.example.servicediplom.event.dto;

import com.example.servicediplom.category.dto.CategoryDto;
import com.example.servicediplom.event.enums.State;
import com.example.servicediplom.event.Location;
import com.example.servicediplom.user.dto.UserShortDto;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class EventFullDto {
    String annotation;

    CategoryDto category;

    int confirmedRequests;

    LocalDateTime createdOn;

    String description;

    LocalDateTime eventDate;

    int id;

    UserShortDto initiator;

    Location location;

    boolean paid;

    int participantLimit;

    LocalDateTime publishedOn;

    boolean requestModeration;

    State state;

    String title;

    int views;
}
