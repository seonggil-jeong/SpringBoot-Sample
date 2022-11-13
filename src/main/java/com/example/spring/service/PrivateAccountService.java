package com.example.spring.service;

import com.example.spring.repository.entity.AccountEntity;

/**
 * use in Service layer
 */
public interface PrivateAccountService {
    AccountEntity getAccountEntityById(final String userId) throws Exception;

}
