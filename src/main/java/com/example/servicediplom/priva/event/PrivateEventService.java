package com.example.servicediplom.priva.event;

import com.example.servicediplom.dto.event.*;

import java.util.List;

public interface PrivateEventService {

    EventFullDto createEvent(Long userId, NewEventDto newEventDto);

    EventFullDto updateEventUser(Long eventId, Long userId, UpdateEventUserRequest updateEventUserRequest);

    EventRequestStatusUpdateResult StatusChangesInTheEvent(Long eventId, Long userId, EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest);

    List<EventShortDto> getEventsCurrentUser(Long userId, Integer from, Integer size);

    EventFullDto getFullInformationEventCurrentUser(Long userId, Long eventId);

    List<ParticipationRequestDto> getInformationAboutRequestsParticipateEventCurrentUser(Long userId, Long eventId);
}
