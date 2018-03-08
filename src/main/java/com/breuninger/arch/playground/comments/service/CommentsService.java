package com.breuninger.arch.playground.comments.service;

import com.breuninger.arch.playground.comments.domain.Comment;
import com.breuninger.arch.playground.comments.domain.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

  private CommentsRepository commentsRepository;

  @Autowired
  public CommentsService(CommentsRepository commentsRepository) {
  }

  public void saveComment(Comment comment) {
    commentsRepository.create(comment);
  }
}
