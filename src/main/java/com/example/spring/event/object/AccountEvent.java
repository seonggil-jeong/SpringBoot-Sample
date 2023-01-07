package com.example.spring.event.object;

import com.example.spring.enums.EventType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public abstract class AccountEvent extends Event {
    private final EventType eventType;

    public AccountEvent(LocalDate createAt, EventType eventType) {
        super(createAt);
        this.eventType = eventType;
    }
}
