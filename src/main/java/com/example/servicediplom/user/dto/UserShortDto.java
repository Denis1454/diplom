package com.example.servicediplom.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserShortDto {
    int id;
    @NotBlank(message = "Имя не может быть пустым")
    String name;
}
