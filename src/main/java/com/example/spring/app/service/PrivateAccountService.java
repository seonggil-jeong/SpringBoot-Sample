package com.example.spring.app.service;

import com.example.spring.app.repository.model.AccountEntity;

/**
 * use in Service layer
 */
public interface PrivateAccountService {
    AccountEntity getAccountEntityById(final String userId) throws Exception;

}
