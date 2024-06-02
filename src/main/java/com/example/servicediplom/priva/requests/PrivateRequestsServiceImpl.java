package com.example.servicediplom.priva.requests;

import com.example.servicediplom.dto.event.ParticipationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivateRequestsServiceImpl implements PrivateRequestsService {


    @Override
    public List<ParticipationRequestDto> getInformationAboutRequestsUserParticipateInStrangersEvents(Long userId) {
        return null;
    }

    @Override
    public ParticipationRequestDto createRequestUserTheEvent(Long userId, Long eventId) {
        return null;
    }

    @Override
    public ParticipationRequestDto cancelingYourRequestToParticipateInEvent(Long userId, Long requestId) {
        return null;
    }
}
