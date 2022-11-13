package com.example.spring.service;

import com.example.spring.repository.entity.PostEntity;

/**
 * use in Service layer
 */
public interface PrivatePostService {
    PostEntity getPostEntityByPostSeq(final long postSeq) throws Exception;
}
