package com.example.servicediplom.pub.event;

import com.example.servicediplom.dto.event.EventFullDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicEventServiceImpl implements PublicEventService {

    @Override
    public List<EventFullDto> getEventsTheAbilityFilter(String text, List<Integer> categories, boolean paid, String rangeStart,
                                                        String rangeEnd, boolean onlyAvailable, String sort, Integer from, Integer size) {
        return null;
    }

    @Override
    public EventFullDto getInformationEventById(Long id) {
        return null;
    }
}
