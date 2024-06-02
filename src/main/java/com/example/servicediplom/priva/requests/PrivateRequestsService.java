package com.example.servicediplom.priva.requests;

import com.example.servicediplom.dto.event.*;

import java.util.List;

public interface PrivateRequestsService {

    List<ParticipationRequestDto> getInformationAboutRequestsUserParticipateInStrangersEvents(Long userId);

    ParticipationRequestDto createRequestUserTheEvent(Long userId, Long eventId);

    ParticipationRequestDto cancelingYourRequestToParticipateInEvent(Long userId, Long requestId);
}
