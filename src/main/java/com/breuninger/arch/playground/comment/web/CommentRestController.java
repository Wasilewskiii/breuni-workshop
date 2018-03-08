package com.breuninger.arch.playground.comment.web;

import com.breuninger.arch.playground.comment.domain.Comment;
import com.breuninger.arch.playground.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.breuninger.arch.playground.common.domain.BadRequestException.badRequest;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/comment")
public class CommentRestController {

  private final Validator validator;
  private final CommentService commentService;

  public CommentRestController(final Validator validator, final CommentService commentService) {
    this.validator = validator;
    this.commentService = commentService;
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Comment> create(@RequestBody final Comment comment, final Errors errors) {
    final Comment sanitizedComment = comment.sanitize();
    validator.validate(sanitizedComment, errors);
    if (errors.hasErrors()) {
      throw badRequest(errors);
    }

    final Comment createdComment = commentService.saveComment(sanitizedComment);
    return ResponseEntity.ok().contentType(APPLICATION_JSON).body(createdComment);
  }
}
