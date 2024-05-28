package com.example.servicediplom.event;

import com.example.servicediplom.category.Category;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Event {
    int id;

    String annotation;

    Category category;

    String description;

    LocalDateTime eventDate;

    Location location;

    boolean paid;

    int participantLimit;

    boolean requestModeration;

    String title;
}
