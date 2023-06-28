package com.example.spring.app.repository.model;

import com.example.spring.enums.EventType;
import lombok.*;

import jakarta.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "event_log")
@Entity
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventSeq;

    @Column(name = "event_id", nullable = false)
    private String eventId;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventType eventType;


    @Column(name = "event_entity", nullable = false)
    private String eventEntity;

    @Builder
    public EventEntity(Long eventSeq, UUID eventId, EventType eventType, String eventEntity) {
        this.eventSeq = eventSeq;
        this.eventId = eventId.toString();
        this.eventType = eventType;
        this.eventEntity = eventEntity;
    }
}
