package com.example.servicediplom.mapper;

import com.example.servicediplom.dto.event.ParticipationRequestDto;
import com.example.servicediplom.entities.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapperDto {
    @Mapping(target = "event",source = "event.id")
    @Mapping(target = "requester",source = "requester.id")
    List<ParticipationRequestDto> toDto(List<Request> requests);
    @Mapping(target = "event",source = "event.id")
    @Mapping(target = "requester",source = "requester.id")
    ParticipationRequestDto toRequestDto (Request request);
}
