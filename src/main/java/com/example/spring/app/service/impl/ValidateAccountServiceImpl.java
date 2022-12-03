package com.example.spring.app.service.impl;

import com.example.spring.exception.AccountException;
import com.example.spring.exception.result.AccountErrorResult;
import com.example.spring.app.repository.AccountRepository;
import com.example.spring.app.controller.service.ValidateAccountService;
import com.example.spring.app.service.PrivateValidateService;
import com.example.spring.app.vo.RegisterAccountRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidateAccountServiceImpl implements PrivateValidateService, ValidateAccountService {
    private final AccountRepository accountRepository;


    /**
     * ------------------------------------- for ValidateAccountService -------------------------------------
     */

    /**
     * validate UserId
     * @param userId
     * @return {true : can, false : can't}
     * @throws Exception
     */
    @Override
    public boolean validateUserId(final String userId) throws Exception {
        return !accountRepository.findById(userId).isPresent();
    }

    /**
     * validate nickname
     * @param nickname
     * @return {true : can, false : can't}
     * @throws Exception
     */
    @Override
    public boolean validateNickname(final String nickname) throws Exception {
        return ! accountRepository.findByNickname(nickname).isPresent();
    }



    /**
     * ------------------------------------- for ValidateMethodService -------------------------------------
     */


    /**
     * Check can Register Account
     * @param request {userId, nickname}
     * @throws Exception
     */
    @Override
    public void canRegister(RegisterAccountRequest request) throws Exception {

        if (accountRepository.findByNickname(request.getNickname()).isPresent()) {
            throw new AccountException(AccountErrorResult.DUPLICATED_NICKNAME);
        }

        if (accountRepository.findById(request.getUserId()).isPresent()) {
            throw new AccountException(AccountErrorResult.DUPLICATED_USER_ID);
        }

    }
}
