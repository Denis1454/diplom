package com.example.servicediplom.dto.event;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EventRequestStatusUpdateResult {
    ParticipationRequestDto confirmedRequests;

    ParticipationRequestDto rejectedRequests;
}
