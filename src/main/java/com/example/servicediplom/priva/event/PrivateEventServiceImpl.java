package com.example.servicediplom.priva.event;

import com.example.servicediplom.dto.event.*;
import com.example.servicediplom.entities.Event;
import com.example.servicediplom.entities.Request;
import com.example.servicediplom.entities.User;
import com.example.servicediplom.entities.enums.State;
import com.example.servicediplom.entities.enums.Status;
import com.example.servicediplom.exception.CreatingEventBeforeTwoHoursException;
import com.example.servicediplom.exception.InitiatorException;
import com.example.servicediplom.exception.NotFoundException;
import com.example.servicediplom.exception.StateException;
import com.example.servicediplom.mapper.EventMapperDto;
import com.example.servicediplom.mapper.RequestMapperDto;
import com.example.servicediplom.repository.CategoryRepository;
import com.example.servicediplom.repository.EventRepository;
import com.example.servicediplom.repository.RequestRepository;
import com.example.servicediplom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.servicediplom.entities.enums.State.PENDING;

@Service
@RequiredArgsConstructor
public class PrivateEventServiceImpl implements PrivateEventService {
    private static final Logger log = LoggerFactory.getLogger(PrivateEventServiceImpl.class);
    private final String ERR_MESSAGE_USER_NOT_FOUND = "Пользователя с id=%s не существует";
    private final String ERR_MESSAGE_EVENT_NOT_FOUND = "События с id=%s не существует";
    private final String ERR_MESSAGE_CATEGORY_NOT_FOUND = "Категории с id=%s не существует";
    private final String ERR_MESSAGE_REQUEST_NOT_FOUND = "Запроса с id=%s не существует";
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final EventMapperDto eventMapperDto;
    private final RequestRepository requestRepository;
    private final RequestMapperDto requestMapperDto;

    @Override
    public EventFullDto createEvent(Long userId, NewEventDto newEventDto) {
        log.info(String.format("Начала операции createEvent, userId = %s, %s ", userId, newEventDto));
        User user = findByUserId(userId);
        existsByCategoryId(newEventDto.getCategory());
        Event event = eventMapperDto.toEvent(newEventDto);
        checkEventDate(event);
        event.setInitiator(user);
        event.setState(PENDING);
        event.setConfirmedRequests(0);
        event.setViews(0);
        Event save = eventRepository.save(event);
        EventFullDto eventFullDto = eventMapperDto.toDto(save);
        log.info("Конец операции createEvent" + eventFullDto);
        return eventFullDto;
    }

    @Override
    public EventFullDto updateEventUser(Long eventId, Long userId, UpdateEventUserRequest updateEventUserRequest) {
        log.info(String.format("Начала операции updateEventUser, eventId = %s, userId = %s, %s ", eventId, userId, updateEventUserRequest));
        User user = findByUserId(userId);
        Event event = findByEventId(eventId);
        if (event.getState().equals(State.PUBLISHED)) {
            throw new StateException("Изменить можно только отмененные события или события в состоянии ожидания модерации");
        }
        checkEventDate(event);
        if (!event.getInitiator().equals(user)) {
            throw new InitiatorException("Только создатель события может его изменить");
        }
        eventMapperDto.updateCategoryDto(updateEventUserRequest, event);
        Event save = eventRepository.save(event);
        EventFullDto eventFullDto = eventMapperDto.toDto(save);
        log.info("Конец операции updateEventUser" + eventFullDto);
        return eventFullDto;
    }

    @Override
    public EventRequestStatusUpdateResult StatusChangesInTheEvent(Long eventId, Long userId,
                                                                  EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        List<ParticipationRequestDto> confirmedRequests = new ArrayList<>();
        List<ParticipationRequestDto> rejectedRequests = new ArrayList<>();

        User user = findByUserId(userId);
        Event event = findByEventId(eventId);
        int countConfirmedRequests = event.getConfirmedRequests();
        Long countRequest = requestRepository.countAllByEvent_Id(event.getId());

        if (!event.getInitiator().equals(user)) {
            throw new InitiatorException("Только создатель события может изменить статус заявки");
        }
        if (event.getParticipantLimit() == 0 && !event.isRequestModeration()) {
            confirmedRequests = checkUnlimitedEvent(eventRequestStatusUpdateRequest.getRequestIds(),event);
        }
        int limit = 0;
        for (Integer requestId : eventRequestStatusUpdateRequest.getRequestIds()) {
            Request request = findByRequestId(requestId);
            limit++;
            if (!request.getStatus().equals(Status.PENDING)) {
                throw new StateException(String.format("Невозможно изменить статус у заявки id=%s, которая в состоянии ожидания", request.getId()));
            }
            if (countRequest >= event.getParticipantLimit()) {
                request.setStatus(Status.REJECTED);
                event.setConfirmedRequests(countConfirmedRequests ++);
                requestRepository.save(request);
                rejectedRequests.add(requestMapperDto.toRequestDto(request));
            } else {
                request.setStatus(Status.CONFIRMED);
                requestRepository.save(request);
                confirmedRequests.add(requestMapperDto.toRequestDto(request));
            }
        }
        eventRepository.save(event);
        if (limit > event.getParticipantLimit()) {
            throw new StateException("Нельзя подтвердить заявку, если уже достигнут лимит по заявкам на данное событие");
        }
        return EventRequestStatusUpdateResult.builder()
                .confirmedRequests(confirmedRequests)
                .rejectedRequests(rejectedRequests)
                .build();
    }

    @Override
    public List<EventShortDto> getEventsCurrentUser(Long userId, Integer from, Integer size) {
        log.info(String.format("Начала операции getEventsCurrentUser, userId = %s", userId));
        existsByUserId(userId);
        Pageable pageable = PageRequest.of(from, size);
        List<Event> events = eventRepository.findAllByInitiator_Id(userId, pageable);
        List<EventShortDto> eventShortDto = eventMapperDto.toFullDto(events);
        log.info("Конец операции getEventsCurrentUser" + eventShortDto);
        return eventShortDto;
    }

    @Override
    public EventFullDto getFullInformationEventCurrentUser(Long eventId, Long userId) {
        log.info(String.format("Начала операции  getFullInformationEventCurrentUser, userId = %s, eventId = %s", userId, eventId));
        existsByUserId(userId);
        existsByEventId(eventId);
        Event eventAndUserId = eventRepository.findByInitiator_IdAndId(userId, eventId);
        EventFullDto eventFullDto = eventMapperDto.toDto(eventAndUserId);
        log.info("Конец операции  getFullInformationEventCurrentUser" + eventFullDto);
        return eventFullDto;
    }

    @Override
    public List<ParticipationRequestDto> getInformationAboutRequestsParticipateEventCurrentUser(Long eventId, Long userId) {
        log.info(String.format("Начала операции getInformationAboutRequestsParticipateEventCurrentUser, userId = %s, eventId = %s", userId, eventId));
        User user = findByUserId(userId);
        List<Request> request = requestRepository.findAllByEvent_IdAndRequester(eventId, user);
        List<ParticipationRequestDto> participationRequestDtoList = requestMapperDto.toDto(request);
        log.info("Конец операции getInformationAboutRequestsParticipateEventCurrentUser" + participationRequestDtoList);
        return participationRequestDtoList;
    }

    private void checkEventDate(Event event) {
        if (!event.getEventDate().isBefore(LocalDateTime.now().plusHours(2))) {
            throw new CreatingEventBeforeTwoHoursException("Нельзя создать событие за 2 часа до начала");
        }
    }

    /**
     * Метод выполняет поиск пользователя по Id
     *
     * @param userId - индификатор пользователя
     * @return - возвращается найденного пользователя
     */
    private User findByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format(ERR_MESSAGE_USER_NOT_FOUND, userId)));
    }

    private void existsByEventId(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new NotFoundException(String.format(ERR_MESSAGE_EVENT_NOT_FOUND, eventId));
        }
    }

    private List<ParticipationRequestDto> checkUnlimitedEvent(List<Integer> requestIds, Event event) {
        List<ParticipationRequestDto> participationRequestDtoList = new ArrayList<>();
        int confirmedRequests = event.getConfirmedRequests();
        for (Integer requestId : requestIds) {
            Request request = findByRequestId(requestId);
            if (!request.getStatus().equals(Status.PENDING)) {
                throw new StateException(String.format("Невозможно изменить статус у заявки id=%s, которая в состоянии ожидания", request.getId()));
            }
            request.setStatus(Status.CONFIRMED);
            event.setConfirmedRequests(confirmedRequests ++);
            requestRepository.save(request);
            participationRequestDtoList.add(requestMapperDto.toRequestDto(request));
        }
        eventRepository.save(event);
        return participationRequestDtoList;
    }

    private Request findByRequestId(Integer requestId) {
        return requestRepository.findById(Long.valueOf(requestId))
                .orElseThrow(() -> new NotFoundException(String.format(ERR_MESSAGE_REQUEST_NOT_FOUND, requestId)));
    }

    private void existsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(String.format(ERR_MESSAGE_USER_NOT_FOUND, userId));
        }
    }

    private Event findByEventId(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(String.format(ERR_MESSAGE_EVENT_NOT_FOUND, eventId)));
    }

    private void existsByCategoryId(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException(String.format(ERR_MESSAGE_CATEGORY_NOT_FOUND, categoryId));
        }
    }
}
