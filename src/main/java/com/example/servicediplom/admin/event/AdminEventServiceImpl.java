package com.example.servicediplom.admin.event;

import com.example.servicediplom.dto.event.EventFullDto;
import com.example.servicediplom.dto.event.UpdateEventAdminRequest;
import com.example.servicediplom.entities.Event;
import com.example.servicediplom.entities.enums.State;
import com.example.servicediplom.entities.enums.StateAdminAction;
import com.example.servicediplom.exception.CreatingEventBeforeTwoHoursException;
import com.example.servicediplom.exception.NotFoundException;
import com.example.servicediplom.exception.StateException;
import com.example.servicediplom.mapper.EventMapperDto;
import com.example.servicediplom.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminEventServiceImpl implements AdminEventService {
    private static final Logger log = LoggerFactory.getLogger(AdminEventServiceImpl.class);
    private final String ERR_MESSAGE_EVENT_NOT_FOUND = "События с id=%s не существует";
    private final EventRepository eventRepository;
    private final EventMapperDto eventMapper;

    @Override
    public List<EventFullDto> searchEvent(List<Long> users, List<State> states, List<Long> categories,
                                          String rangeStart, String rangeEnd, Integer from, Integer size) {
        log.info("Начало операции searchEvent");
        Pageable pageable = PageRequest.of(from, size);
        List<Event> events = eventRepository.findAllByCategoryIdInAndInitiatorIdInAndStateIn(categories,users, states, pageable)
                .stream()
                .filter(event -> event.getEventDate().isAfter(LocalDateTime.parse(rangeStart))
                        &&  event.getEventDate().isBefore(LocalDateTime.parse(rangeEnd)))
                .toList();
        List<EventFullDto> eventFullDto = eventMapper.toListDto(events);
        log.info("Конец операции searchEvent" + eventFullDto);
        return eventFullDto;
    }

    @Override
    public EventFullDto updateAdminEvent(Long eventId, UpdateEventAdminRequest updateEventAdminRequest) {
        log.info(String.format("Начало операции updateAdminEvent, eventId = %s, %s ", eventId,updateEventAdminRequest));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(String.format(ERR_MESSAGE_EVENT_NOT_FOUND, eventId)));
        if (event.getEventDate().isBefore(LocalDateTime.now().plusHours(1))) {
            throw new CreatingEventBeforeTwoHoursException("Нельзя редактировать событие за 1 час до начала");
        }
        if (!event.getState().equals(State.PENDING)) {
            throw new StateException("Событие можно публиковать, только если оно в состоянии ожидания публикации");
        }
        if (!event.getState().equals(State.PUBLISHED)) {
            throw new StateException("Событие можно отклонить, только если оно еще не опубликовано");
        }
        if (updateEventAdminRequest.getStateAction().equals(StateAdminAction.REJECT_EVENT.toString())) {
            event.setState(State.CANCELED);
        }
        if (updateEventAdminRequest.getStateAction().equals(StateAdminAction.PUBLISH_EVENT.toString())) {
            event.setState(State.PUBLISHED);
        }
        eventMapper.updateEventAdminDto(updateEventAdminRequest, event);
        Event save = eventRepository.save(event);
        EventFullDto eventFullDto = eventMapper.toDto(save);
        log.info("Конец операции updateAdminEvent" + eventFullDto);
        return eventFullDto;
    }
}
