package com.example.spring.event;

import com.example.spring.enums.EventType;
import com.example.spring.event.object.AccountEvent;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.usertype.UserType;

import java.time.LocalDate;
@Getter
public final class LoginAccountEvent extends AccountEvent {
    private final String userId;
    private final String userRole;

    @Builder
    public LoginAccountEvent(LocalDate createAt, EventType eventType, String userId, String userRole) {
        super(createAt, eventType);
        this.userId = userId;
        this.userRole = userRole;
    }
}
