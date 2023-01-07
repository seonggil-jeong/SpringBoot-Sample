package com.example.spring.event;

import com.example.spring.enums.EventType;
import com.example.spring.event.object.AccountEvent;
import com.example.spring.event.object.Event;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public final class CreateAccountEvent extends AccountEvent {
    private final String userId;
    private final String userName;

    @Builder
    public CreateAccountEvent(LocalDate createAt, EventType eventType, String userId, String userName) {
        super(createAt, eventType);
        this.userId = userId;
        this.userName = userName;
    }
}
