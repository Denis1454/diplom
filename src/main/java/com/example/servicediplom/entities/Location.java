package com.example.servicediplom.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Location {
    private float lat;

    private float lon;
}
