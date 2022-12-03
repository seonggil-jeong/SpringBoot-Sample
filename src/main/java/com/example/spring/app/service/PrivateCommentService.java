package com.example.spring.app.service;

import com.example.spring.app.repository.model.Comment;

import java.util.List;

/**
 * use in Service layer
 */
public interface PrivateCommentService {

    List<Comment> findCommentByPostSeq(final Long postSeq) throws Exception;
}
