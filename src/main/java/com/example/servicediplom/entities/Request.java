package com.example.servicediplom.entities;

import com.example.servicediplom.entities.enums.Status;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "participation_request")
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created")
    private LocalDateTime created;
    @ManyToOne
    @JoinColumn(name = "event")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "requester")
    private User requester;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
