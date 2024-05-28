package com.example.servicediplom.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    int id;
    @NotBlank(message = "Имя не может быть пустым")
    String name;
    @Email
    @NotBlank(message = "email не может быть пустым")
    String email;
}
