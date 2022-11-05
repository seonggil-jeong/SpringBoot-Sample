package com.example.spring.services;

import com.example.spring.repositories.entities.AccountEntity;

/**
 * use in Service layer
 */
public interface PrivateAccountService {
    AccountEntity getAccountEntityById(final String userId) throws Exception;

}
