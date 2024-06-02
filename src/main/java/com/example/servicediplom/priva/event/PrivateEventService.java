package com.example.servicediplom.priva.event;

import com.example.servicediplom.dto.event.EventFullDto;
import com.example.servicediplom.dto.event.EventRequestStatusUpdateRequest;
import com.example.servicediplom.dto.event.NewEventDto;
import com.example.servicediplom.dto.event.UpdateEventUserRequest;

import java.util.List;

public interface PrivateEventService {

    EventFullDto create(Long userId, NewEventDto newEventDto);

    EventFullDto updateEventUser(Long eventId, Long userId, UpdateEventUserRequest updateEventUserRequest);

    EventFullDto StatusChangesInTheEvent(Long eventId, Long userId, EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest);

    List<EventFullDto> getEventsCurrentUser(Long userId, Integer from, Integer size);

    EventFullDto getFullInformationEventCurrentUser(Long userId, Long eventId);

    List<EventFullDto> getInformationAboutRequestsParticipateEventCurrentUser(Long userId, Long eventId);
}
