package com.example.servicediplom.dto.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserShortDto {
    Long id;

    String name;
}
