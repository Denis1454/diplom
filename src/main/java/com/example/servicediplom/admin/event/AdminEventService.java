package com.example.servicediplom.admin.event;

import com.example.servicediplom.dto.event.EventFullDto;
import com.example.servicediplom.dto.event.UpdateEventAdminRequest;
import com.example.servicediplom.entities.enums.State;

import java.util.List;

public interface AdminEventService {

    List<EventFullDto> searchEvent(List<Long> users, List<State> states, List<Long> categories,
                                   String rangeStart, String rangeEnd, Integer from, Integer size);


    EventFullDto updateAdminEvent(Long eventId, UpdateEventAdminRequest updateEventAdminRequest);
}
