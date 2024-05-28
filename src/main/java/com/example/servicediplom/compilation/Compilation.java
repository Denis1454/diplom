package com.example.servicediplom.compilation;

import com.example.servicediplom.event.Event;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Compilation {
    int id;

    Event event;

    boolean pinned;

    String title;
}
