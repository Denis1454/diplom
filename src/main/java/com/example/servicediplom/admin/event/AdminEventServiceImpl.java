package com.example.servicediplom.admin.event;

import com.example.servicediplom.dto.event.EventFullDto;
import com.example.servicediplom.dto.event.UpdateEventAdminRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminEventServiceImpl implements AdminEventService {

    @Override
    public List<EventFullDto> searchEvent(List<Integer> users, List<String> states, List<Integer> categories,
                                          String rangeStart, String rangeEnd, Integer from, Integer size) {
        return null;
    }

    @Override
    public EventFullDto updateAdminEvent(Long eventId, UpdateEventAdminRequest updateEventAdminRequest) {
        return null;
    }
}
