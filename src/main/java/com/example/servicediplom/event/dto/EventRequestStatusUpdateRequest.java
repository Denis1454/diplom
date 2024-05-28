package com.example.servicediplom.event.dto;

import com.example.servicediplom.event.enums.Status;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class EventRequestStatusUpdateRequest {
    List<Integer> requestIds;

    Status status;
}
