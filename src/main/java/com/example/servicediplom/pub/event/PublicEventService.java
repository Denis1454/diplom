package com.example.servicediplom.pub.event;

import com.example.servicediplom.dto.event.EventFullDto;
import com.example.servicediplom.dto.event.EventShortDto;

import java.util.List;

public interface PublicEventService {

    List<EventShortDto> getEventsTheAbilityFilter(String text, List<Long> categories, boolean paid, String rangeStart,
                                                  String rangeEnd, boolean onlyAvailable, String sort, Integer from, Integer size);

    EventFullDto getInformationEventById(Long id);
}
