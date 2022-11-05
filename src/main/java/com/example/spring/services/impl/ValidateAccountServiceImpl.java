package com.example.spring.services.impl;

import com.example.spring.exceptions.AccountException;
import com.example.spring.exceptions.results.AccountErrorResult;
import com.example.spring.repositories.AccountRepository;
import com.example.spring.controllers.services.ValidateAccountService;
import com.example.spring.services.PrivateValidateService;
import com.example.spring.vo.RegisterAccountRequest;
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
