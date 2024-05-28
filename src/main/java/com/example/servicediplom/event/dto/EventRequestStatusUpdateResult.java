package com.example.servicediplom.event.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EventRequestStatusUpdateResult {
    ParticipationRequestDto confirmedRequests;

    ParticipationRequestDto rejectedRequests;
}
