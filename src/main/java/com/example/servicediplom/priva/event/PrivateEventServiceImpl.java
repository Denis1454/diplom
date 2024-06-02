package com.example.servicediplom.priva.event;

import com.example.servicediplom.dto.event.EventFullDto;
import com.example.servicediplom.dto.event.EventRequestStatusUpdateRequest;
import com.example.servicediplom.dto.event.NewEventDto;
import com.example.servicediplom.dto.event.UpdateEventUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivateEventServiceImpl implements PrivateEventService {

    @Override
    public EventFullDto create(Long userId, NewEventDto newEventDto) {
        return null;
    }

    @Override
    public EventFullDto updateEventUser(Long eventId, Long userId, UpdateEventUserRequest updateEventUserRequest) {
        return null;
    }

    @Override
    public EventFullDto StatusChangesInTheEvent(Long eventId, Long userId, EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        return null;
    }

    @Override
    public List<EventFullDto> getEventsCurrentUser(Long userId, Integer from, Integer size) {
        return null;
    }

    @Override
    public EventFullDto getFullInformationEventCurrentUser(Long userId, Long eventId) {
        return null;
    }

    @Override
    public List<EventFullDto> getInformationAboutRequestsParticipateEventCurrentUser(Long userId, Long eventId) {
        return null;
    }
}
