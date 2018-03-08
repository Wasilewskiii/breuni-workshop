package com.breuninger.arch.playground.comment.service;

import com.breuninger.arch.playground.comment.domain.Comment;
import com.breuninger.arch.playground.comment.domain.CommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentsServiceTest {

  @Autowired
  private CommentService commentsService;
  @Autowired
  private CommentRepository commentRepository;

  @Test
  public void shouldStoreComment() {
    Comment comment = Comment.builder().cardId("exampleCardId").text("some text").build();
    commentsService.saveComment(comment);

    List<Comment> savedComments = commentRepository.findCommentsByCardId("exampleCardId");

    assertEquals(comment.getCardId(), savedComments.get(0).getCardId());
    assertTrue(comment.equals(savedComments.get(0)));
  }
}
