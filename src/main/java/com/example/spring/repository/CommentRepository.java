package com.example.spring.repository;

import com.example.spring.repository.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findAllByPostSeqOrderByPostSeqDesc(Long postSeq);

    List<Comment> findAllByUserId(String userId);
}
