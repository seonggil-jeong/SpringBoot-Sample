package com.example.spring.app.service;

import com.example.spring.app.repository.model.PostEntity;

/**
 * use in Service layer
 */
public interface PrivatePostService {
    PostEntity getPostEntityByPostSeq(final long postSeq) throws Exception;
}
