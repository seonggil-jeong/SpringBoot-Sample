package com.example.spring.app.service.impl;

import com.example.spring.app.controller.service.CommentService;
import com.example.spring.app.repository.CommentRepository;
import com.example.spring.app.repository.model.Comment;
import com.example.spring.app.service.PrivateAccountService;
import com.example.spring.app.service.PrivateCommentService;
import com.example.spring.app.vo.CreateCommentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements PrivateCommentService, CommentService {
    private final CommentRepository commentRepository;
    private final PrivateAccountService accountService;


    /**
     * ------------------------------------- for PrivateCommentService -------------------------------------
     */
    @Override
    public List<Comment> findCommentByPostSeq(final Long postSeq) throws Exception {
        return commentRepository.findAllByPostSeqOrderByPostSeqDesc(postSeq);
    }


    /**
     * ------------------------------------- for CommentService -------------------------------------
     */

    @Override
    public void createComment(CreateCommentRequest request, final String userId, long postSeq) throws Exception {

        commentRepository.save(Comment.builder()
                .userId(userId)
                .createDate(new Date())
                .content(request.getContent())
                .postSeq(postSeq).build());


    }

    @Override
    public List<Comment> findCommentByUserId(final String userId) throws Exception {
        return commentRepository.findAllByUserId(userId);
    }

    @Override
    public void deleteComment(final String userId, final String commentId) throws Exception {
        commentRepository.deleteById(commentId);
    }
}
