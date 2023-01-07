package com.example.spring.app.event.listener;

import com.example.spring.app.repository.EventRepository;
import com.example.spring.app.repository.model.EventEntity;
import com.example.spring.event.object.AccountEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountEventLoggingListener {
    private final EventRepository eventRepository;

    @EventListener
    public void loggingAccountEvent(AccountEvent event) {
        log.info(this.getClass().getName() + ".account event logging Start!");

        eventRepository.save(EventEntity.builder()
                .eventId(event.getEventId())
                .eventType(event.getEventType())
                .eventEntity("ACCOUNT_EVENT").build());
    }
}
