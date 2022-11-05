package com.example.spring.services;

import com.example.spring.repositories.entities.PostEntity;
import com.example.spring.repositories.models.Comment;

import java.util.List;

/**
 * use in Service layer
 */
public interface PrivateCommentService {

    List<Comment> findCommentByPostSeq(final Long postSeq) throws Exception;
}
