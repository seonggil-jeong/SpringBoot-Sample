package com.example.spring.event.object;

import com.example.spring.enums.EventType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@ToString
public abstract class Event {
    private final UUID eventId;
    private final LocalDate createAt;

    public Event(LocalDate createAt) {
        this.eventId = UUID.randomUUID();
        this.createAt = createAt;
    }
}
