package com.example.servicediplom.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class User {
    int id;

    String name;

    String email;
}
