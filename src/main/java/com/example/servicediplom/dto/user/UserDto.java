package com.example.servicediplom.dto.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    int id;

    String name;

    String email;
}
