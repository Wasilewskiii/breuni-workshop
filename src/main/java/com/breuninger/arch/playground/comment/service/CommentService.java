package com.breuninger.arch.playground.comment.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.breuninger.arch.playground.comment.domain.Comment;
import com.breuninger.arch.playground.comment.domain.CommentRepository;
import com.codahale.metrics.annotation.Gauge;

@Service
public class CommentService {

  private final CommentRepository commentRepository;

  public CommentService(final CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public List<Comment> findByCard(String id) {
    return commentRepository.findCommentsByCardId(id);
  }

  public Comment create(final Comment comment) {
    final Date creationDate = new Date();
    return commentRepository.create(comment.toBuilder()
      .id(ObjectId.get().toString())
      .creationDate(creationDate)
      .lastModificationDate(creationDate)
      .build());
  }

  @Gauge(name = "comment.count", absolute = true)
  public long count() {
    return commentRepository.size();
  }
}
