package com.example.servicediplom.admin.event;

import com.example.servicediplom.dto.event.EventFullDto;
import com.example.servicediplom.dto.event.UpdateEventAdminRequest;

import java.util.List;

public interface AdminEventService {

    List<EventFullDto> searchEvent(List<Integer> users, List<String> states, List<Integer> categories,
                                   String rangeStart, String rangeEnd, Integer from, Integer size);


    EventFullDto updateAdminEvent(Long eventId, UpdateEventAdminRequest updateEventAdminRequest);
}
