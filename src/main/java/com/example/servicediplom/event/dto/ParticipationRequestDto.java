package com.example.servicediplom.event.dto;

import com.example.servicediplom.event.Event;
import com.example.servicediplom.event.enums.State;

import com.example.servicediplom.user.User;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class ParticipationRequestDto {
    LocalDateTime created;

    int event;

    int id;

    User requesterId;

    String status; //TODO Тут нужен именно PENDING или просто статус??
}
