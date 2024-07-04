package com.example.servicediplom.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "compilations")
@Entity
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(
            name = "compilations_events",
            joinColumns = {@JoinColumn(name = "compilation_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")}
    )
    private List<Event> event;
    @Column(name = "is_pinned")
    private boolean pinned;
    @Column(name = "title")
    private String title;
}
