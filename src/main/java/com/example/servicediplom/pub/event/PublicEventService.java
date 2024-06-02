package com.example.servicediplom.pub.event;

import com.example.servicediplom.dto.event.EventFullDto;

import java.util.List;

public interface PublicEventService {

    List<EventFullDto> getEventsTheAbilityFilter(String text, List<Integer> categories, boolean paid, String rangeStart,
                                                 String rangeEnd, boolean onlyAvailable, String sort, Integer from, Integer size);

    EventFullDto getInformationEventById(Long id);
}
