package com.example.servicediplom.priva.event;

import com.example.servicediplom.dto.event.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}/events")
@RequiredArgsConstructor
public class PrivateEventController {

    private final PrivateEventService privateEventService;

    @PostMapping()
    public EventFullDto create(@PathVariable Long userId, @RequestBody NewEventDto newEventDto) {
        return privateEventService.createEvent(userId, newEventDto);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEventUser(@PathVariable Long eventId,
                                        @PathVariable Long userId,
                                        @RequestBody @Validated UpdateEventUserRequest updateEventUserRequest) {
        return privateEventService.updateEventUser(eventId, userId, updateEventUserRequest);
    }

    @PatchMapping("/{eventId}/requests")
    public EventRequestStatusUpdateResult StatusChangesInTheEvent(@PathVariable Long eventId,
                                                @PathVariable Long userId,
                                                @RequestBody EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        return privateEventService.StatusChangesInTheEvent(eventId, userId, eventRequestStatusUpdateRequest);
    }

    @GetMapping()
    public List<EventShortDto> getEventsCurrentUser(@PathVariable Long userId,
                                                   @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
                                                   @RequestParam(required = false, defaultValue = "10") @Positive Integer size) {
        return privateEventService.getEventsCurrentUser(userId, from, size);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getFullInformationEventCurrentUser(@PathVariable Long userId,
                                                           @PathVariable Long eventId) {
        return privateEventService.getFullInformationEventCurrentUser(userId, eventId);
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getInformationAboutRequestsParticipateEventCurrentUser(@PathVariable Long userId,
                                                                                     @PathVariable Long eventId) {
        return privateEventService.getInformationAboutRequestsParticipateEventCurrentUser(userId, eventId);
    }
}
