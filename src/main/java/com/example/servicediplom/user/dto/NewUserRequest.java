package com.example.servicediplom.user.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewUserRequest {
    String email;

    String name;
}
