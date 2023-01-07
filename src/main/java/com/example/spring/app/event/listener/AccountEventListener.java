package com.example.spring.app.event.listener;

import com.example.spring.app.repository.AccountRepository;
import com.example.spring.app.repository.EventRepository;
import com.example.spring.app.repository.PostRepository;
import com.example.spring.app.repository.model.AccountEntity;
import com.example.spring.app.repository.model.EventEntity;
import com.example.spring.app.repository.model.PostEntity;
import com.example.spring.event.CreateAccountEvent;
import com.example.spring.event.LoginAccountEvent;
import com.example.spring.event.object.AccountEvent;
import com.example.spring.event.object.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.NoSuchElementException;


@Slf4j
@Component
@RequiredArgsConstructor
public class AccountEventListener {
    private final AccountRepository accountRepository;
    private final PostRepository postRepository;
    private final EventRepository eventRepository;

    @EventListener // when createAccount
    public void pushEmailListener(CreateAccountEvent event) {
        log.info(this.getClass().getName() + ".pushEmailListener Start");

        log.info("userId : " + event.getUserId());
        log.info("userName : " + event.getUserName());

        System.out.println("push Email To " + event.getUserName() + " Hello!");
    }

    @EventListener // when createAccount
    @Transactional(rollbackFor = Exception.class)
    public void createDefaultPost(CreateAccountEvent event) {

        final AccountEntity account = accountRepository.findById(event.getUserId()).orElseThrow(()
                -> new NoSuchElementException("cannot found account with id : " + event.getUserId()));

        postRepository.save(PostEntity.builder()
                .createDate(new Date())
                .postContent("default Post")
                .postTitle("Default Post from Listener")
                .createUserId(account).build());

    }

    @EventListener // when login success
    public void checkAccountLogin(LoginAccountEvent event) {
        log.info(this.getClass().getName() + ".user Login Completed");
        log.info("userId : " + event.getUserId());
        log.info("userRole : " + event.getUserRole());
    }
}
