package com.example.spring.services;

import com.example.spring.repositories.entities.PostEntity;

/**
 * use in Service layer
 */
public interface PrivatePostService {
    PostEntity getPostEntityByPostSeq(final long postSeq) throws Exception;
}
