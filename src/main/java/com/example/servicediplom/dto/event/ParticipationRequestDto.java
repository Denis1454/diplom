package com.example.servicediplom.dto.event;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ParticipationRequestDto {
    String created;

    Long event;

    Long id;

    Long requester;

    String status;
}
