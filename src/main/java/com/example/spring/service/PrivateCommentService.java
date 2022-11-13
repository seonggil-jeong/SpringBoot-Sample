package com.example.spring.service;

import com.example.spring.repository.model.Comment;

import java.util.List;

/**
 * use in Service layer
 */
public interface PrivateCommentService {

    List<Comment> findCommentByPostSeq(final Long postSeq) throws Exception;
}
