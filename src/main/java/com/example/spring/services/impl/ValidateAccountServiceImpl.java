package com.example.spring.services.impl;

import com.example.spring.exceptions.AccountException;
import com.example.spring.exceptions.results.AccountErrorResult;
import com.example.spring.repositories.AccountRepository;
import com.example.spring.services.ValidateAccountService;
import com.example.spring.services.ValidateMethodService;
import com.example.spring.vo.RegisterAccountRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidateAccountServiceImpl implements ValidateMethodService, ValidateAccountService {
    private final AccountRepository accountRepository;


    @Override
    public boolean validateUserId(final String userId) throws Exception {
        return !accountRepository.findByUserId(userId).isPresent();
    }

    @Override
    public boolean validateNickname(final String nickname) throws Exception {
        return ! accountRepository.findByNickname(nickname).isPresent();
    }

    @Override
    public void canRegister(RegisterAccountRequest request) throws Exception {

        if (accountRepository.findByNickname(request.getNickname()).isPresent()) {
            throw new AccountException(AccountErrorResult.DUPLICATED_NICKNAME);
        }

        if (accountRepository.findByUserId(request.getUserId()).isPresent()) {
            throw new AccountException(AccountErrorResult.DUPLICATED_USER_ID);
        }

    }
}
