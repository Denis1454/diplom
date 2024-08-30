package com.example.servicediplom.priva.requests;

import com.example.servicediplom.dto.event.ParticipationRequestDto;
import com.example.servicediplom.entities.Event;
import com.example.servicediplom.entities.Request;
import com.example.servicediplom.entities.User;
import com.example.servicediplom.entities.enums.State;
import com.example.servicediplom.entities.enums.Status;
import com.example.servicediplom.exception.*;
import com.example.servicediplom.mapper.RequestMapperDto;
import com.example.servicediplom.repository.EventRepository;
import com.example.servicediplom.repository.RequestRepository;
import com.example.servicediplom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivateRequestsServiceImpl implements PrivateRequestsService {
    private static final Logger log = LoggerFactory.getLogger(PrivateRequestsServiceImpl.class);
    private final String ERR_MESSAGE_USER_NOT_FOUND = "Пользователя с id=%s не существует";
    private final String ERR_MESSAGE_EVENT_NOT_FOUND = "События с id=%s не существует";
    private final String ERR_MESSAGE_REQUEST_NOT_FOUND = "Запроса с id=%s не существует";
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final RequestRepository requestRepository;
    private final RequestMapperDto requestMapper;

    @Override
    public List<ParticipationRequestDto> getRequestsByRequesterId(Long userId) {
        log.info(String.format("Начало операции getRequestsByRequesterId, userId = %s ", userId));
        User user = findByUserId(userId);
        List<Request> requester = requestRepository.findAllByRequester(user);
        List<ParticipationRequestDto> participationRequestDtoList = requestMapper.toDto(requester);
        log.info("Конец операции getRequestsByRequesterId" + participationRequestDtoList);
        return participationRequestDtoList;
    }

    @Override
    public ParticipationRequestDto createRequest(Long eventId, Long userId) {
        log.info(String.format("Начало операции createRequest(Private), eventId = %s,userId = %s ", eventId, userId));
        User user = findByUserId(userId);
        Event event = findByEventId(eventId);

        if (requestRepository.existsByEventIdAndRequesterId(eventId, userId)) {
            throw new DuplicateRequestException("Нельзя отправить повторный запрос");
        }
        if (event.getInitiator().equals(user)) {
            throw new InitiatorException("Инициатор события не может добавить запрос на участие в своём событии");
        }
        if (!event.getState().equals(State.PUBLISHED)) {
            throw new UnpublishedEventException("Нельзя участвовать в неопубликованном событии");
        }
        if (event.getConfirmedRequests() == event.getParticipantLimit()) {
            throw new LimitException("Нельзя подать заявку, уже достигнут лимит по заявкам на данное событие");
        }
        if (!event.isRequestModeration()) {
            return saveRequestWhenModerationOff(event, user);
        } else {
            return saveRequestWhenModerationOn(event, user);
        }
    }

    @Override
    public ParticipationRequestDto changeStatusToCancel(Long userId, Long requestId) {
        log.info(String.format("Начало операции changeStatusToCancel, userId = %s, requestId = %s", userId, requestId));
        User user = findByUserId(userId);
        Request request = findByRequestId(requestId);
        if (!request.getRequester().equals(user)) {
            throw new InitiatorException("Только сам пользователь может отменить запрос");
        }
        request.setStatus(Status.CANCELED);
        Request save = requestRepository.save(request);
        ParticipationRequestDto participationRequestDto = requestMapper.toRequestDto(save);
        log.info("Конец операции changeStatusToCancel" + participationRequestDto);
        return participationRequestDto;
    }

    private ParticipationRequestDto saveRequestWhenModerationOff(Event event, User user) {
        Request request = new Request();
        request.setCreated(LocalDateTime.now());
        request.setRequester(user);
        request.setEvent(event);
        request.setStatus(Status.CONFIRMED);
        Request save = requestRepository.save(request);
        ParticipationRequestDto participationRequestDto = requestMapper.toRequestDto(save);
        log.info("Конец операции createRequest " + participationRequestDto);
        return participationRequestDto;
    }

    private ParticipationRequestDto saveRequestWhenModerationOn(Event event, User user) {
        Request request = new Request();
        request.setCreated(LocalDateTime.now());
        request.setRequester(user);
        request.setEvent(event);
        request.setStatus(Status.PENDING);
        Request save = requestRepository.save(request);
        ParticipationRequestDto participationRequestDto = requestMapper.toRequestDto(save);
        log.info("Конец операции createRequest " + participationRequestDto);
        return participationRequestDto;
    }

    private Request findByRequestId(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException(String.format(ERR_MESSAGE_REQUEST_NOT_FOUND, requestId)));
    }

    private User findByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format(ERR_MESSAGE_USER_NOT_FOUND, userId)));
    }

    private Event findByEventId(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(String.format(ERR_MESSAGE_EVENT_NOT_FOUND, eventId)));
    }
}
