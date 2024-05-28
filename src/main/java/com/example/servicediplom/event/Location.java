package com.example.servicediplom.event;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Location {
    float lat;

    float lon;
}
