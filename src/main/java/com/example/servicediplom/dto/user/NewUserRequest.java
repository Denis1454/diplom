package com.example.servicediplom.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewUserRequest {
    @Email
    @Size(min = 6, max = 254)
    String email;
    @Size(min = 2, max = 250)
    String name;
}
