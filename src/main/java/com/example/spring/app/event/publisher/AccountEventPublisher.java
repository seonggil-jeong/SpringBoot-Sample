package com.example.spring.app.event.publisher;

import com.example.spring.enums.EventType;
import com.example.spring.event.CreateAccountEvent;
import com.example.spring.event.LoginAccountEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccountEventPublisher {
    private final ApplicationEventPublisher publisher;


    public void pushCreateUserEvent(final String userId, final String userName) {
        log.info(this.getClass().getName() + ".push create user event start!");
        log.info("userId : " + userId);
        log.info("userName : " + userName);

        publisher.publishEvent(CreateAccountEvent.builder()
                .userName(userName)
                .userId(userId)
                .createAt(LocalDate.now())
                .eventType(EventType.CREATE).build());
    }

    public void pushLoginUserEvent(final String userId, final String userRole) {
        log.info(this.getClass().getName() + ".push login user event Start!");
        log.info("userId : " + userId);
        log.info("userRole : " + userRole);

        publisher.publishEvent(LoginAccountEvent.builder()
                .userId(userId)
                .createAt(LocalDate.now())
                .eventType(EventType.USER_LOGIN)
                .userRole(userRole).build());
    }
}
