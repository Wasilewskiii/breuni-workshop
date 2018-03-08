package com.breuninger.arch.playground.comment.service;

import com.breuninger.arch.playground.comment.domain.Comment;
import com.breuninger.arch.playground.comment.domain.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

  private CommentRepository commentRepository;

  public CommentService(CommentRepository repository) {
    commentRepository = repository;
  }

  public List<Comment> findByCard(String cardId) {
    return commentRepository.findCommentsByCardId(cardId);
  }

  public Comment saveComment(Comment comment) {
   return commentRepository.create(comment);
  }

}
