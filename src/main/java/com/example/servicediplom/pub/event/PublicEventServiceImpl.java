package com.example.servicediplom.pub.event;

import com.example.servicediplom.dto.event.EventFullDto;
import com.example.servicediplom.dto.event.EventShortDto;
import com.example.servicediplom.entities.Category;
import com.example.servicediplom.entities.Event;
import com.example.servicediplom.entities.enums.State;
import com.example.servicediplom.exception.NotFoundException;
import com.example.servicediplom.exception.StateException;
import com.example.servicediplom.mapper.EventMapperDto;
import com.example.servicediplom.repository.CategoryRepository;
import com.example.servicediplom.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicEventServiceImpl implements PublicEventService {
    private static final Logger log = LoggerFactory.getLogger(PublicEventServiceImpl.class);
    private final String ERR_MESSAGE_EVENT_NOT_FOUND = "События с id=%s не существует";
    private final EventRepository eventRepository;
    private final EventMapperDto eventMapperDto;
    private final CategoryRepository categoryRepository;

    @Override
    public List<EventShortDto> getEventsTheAbilityFilter(String text, List<Long> categories, boolean paid, String rangeStart,
                                                         String rangeEnd, boolean onlyAvailable, String sort, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        List<Category> categoriesId = categoryRepository.findAllByIdIn(categories,pageable);
        if(!onlyAvailable) {
            throw new StateException("");//TODO нужен event или нет??
        }
        if (text.equalsIgnoreCase(text)){
            text.toLowerCase();
            //TODO что подразумевается под text заголовок title?
        }
        return null;
    }

    @Override
    public EventFullDto getInformationEventById(Long id) {
        log.info(String.format("Начала операции getInformationEventById, id = %s ", id));
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ERR_MESSAGE_EVENT_NOT_FOUND, id)));
        if (!event.getState().equals(State.PUBLISHED)) {
            throw new StateException("Событие еще не опубликованно");
        }
        EventFullDto eventFullDto = eventMapperDto.toDto(event);
        log.info("Конец операции createCompilation" + eventFullDto);
        return eventFullDto;
    }
}
