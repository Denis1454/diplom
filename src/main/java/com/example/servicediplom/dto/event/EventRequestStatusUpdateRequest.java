package com.example.servicediplom.dto.event;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class EventRequestStatusUpdateRequest {
    List<Integer> requestIds;

    String status;
}
