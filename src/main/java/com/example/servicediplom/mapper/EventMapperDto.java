package com.example.servicediplom.mapper;

import com.example.servicediplom.dto.event.*;
import com.example.servicediplom.entities.Event;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapperDto {
    @Mapping(source = "category.id",target = "category")
    UpdateEventAdminRequest toUpdateEvent(Event event);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category",ignore = true)
    void updateCategoryDto(UpdateEventUserRequest dto, @MappingTarget Event event);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category",ignore = true)
    void updateEventAdminDto(UpdateEventAdminRequest dto, @MappingTarget Event event);
    List<EventShortDto> toFullDto(List<Event> events);
    @Mapping(target = "category",ignore = true)
    Event toEvent(NewEventDto newEventDto);
    EventFullDto toDto(Event event);
    List<EventFullDto> toListDto(List<Event> events);
}
