package com.example.servicediplom.dto.event;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ParticipationRequestDto {
    String created;

    int event;

    int id;

    int requester;

    String status;
}
