package com.example.servicediplom.mapper.event;

import com.example.servicediplom.entities.Event;
import com.example.servicediplom.dto.event.EventFullDto;
import com.example.servicediplom.dto.event.UpdateEventUserRequest;
import com.example.servicediplom.entities.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EventMapperDto {
    EventFullDto toDto(Event event);

    Event toEvent(EventFullDto eventFullDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEventDto(EventFullDto dto, @MappingTarget User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUpdateEvent(UpdateEventUserRequest UpdateEventUserRequest, @MappingTarget Event event);
}
