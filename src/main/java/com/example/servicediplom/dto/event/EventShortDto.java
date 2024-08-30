package com.example.servicediplom.dto.event;


import com.example.servicediplom.dto.category.CategoryDto;
import com.example.servicediplom.dto.user.UserShortDto;
import lombok.Builder;
import lombok.Value;
@Value
@Builder
public class EventShortDto {
    String annotation;

    CategoryDto category;

    int confirmedRequests;

    String eventDate;

    int id;

    UserShortDto initiator;

    boolean paid;

    String title;

    int views;
}
