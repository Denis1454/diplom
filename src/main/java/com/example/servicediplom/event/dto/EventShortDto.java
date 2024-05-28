package com.example.servicediplom.event.dto;


import com.example.servicediplom.category.dto.CategoryDto;
import com.example.servicediplom.user.dto.UserShortDto;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
@Value
@Builder
public class EventShortDto {
    String annotation;

    CategoryDto category;

    int confirmedRequests;

    LocalDateTime eventDate;

    int id;

    UserShortDto initiator;

    boolean paid;

    String title;

    int views;
}
