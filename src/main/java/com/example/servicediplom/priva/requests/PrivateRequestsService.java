package com.example.servicediplom.priva.requests;

import com.example.servicediplom.dto.event.*;

import java.util.List;

public interface PrivateRequestsService {

    List<ParticipationRequestDto> getRequestsByRequesterId(Long userId);

    ParticipationRequestDto createRequest(Long userId, Long eventId);

    ParticipationRequestDto changeStatusToCancel(Long userId, Long requestId);
}
