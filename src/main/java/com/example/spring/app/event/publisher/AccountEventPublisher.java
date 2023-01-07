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


    public void pushCreateUserEvent(CreateAccountEvent event) {
        log.info(this.getClass().getName() + ".push create user event start!");
        log.info("userId : " + event.getUserId());
        log.info("userName : " + event.getUserName());
        log.info("event Type : " + event.getEventType());

        publisher.publishEvent(event);
    }

    public void pushLoginUserEvent(LoginAccountEvent event) {
        log.info(this.getClass().getName() + ".push login user event Start!");
        log.info("userId : " + event.getUserId());
        log.info("userRole : " + event.getUserRole());

        publisher.publishEvent(event);
    }
}
