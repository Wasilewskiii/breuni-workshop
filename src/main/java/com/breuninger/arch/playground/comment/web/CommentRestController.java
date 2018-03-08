package com.breuninger.arch.playground.comment.web;

import static java.util.concurrent.TimeUnit.HOURS;

import static org.springframework.http.CacheControl.maxAge;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import static com.breuninger.arch.playground.common.domain.BadRequestException.badRequest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import com.breuninger.arch.playground.comment.domain.Comment;
import com.breuninger.arch.playground.comment.service.CommentService;
import com.breuninger.arch.playground.toggle.domain.Features;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/comments")
public class CommentRestController {

  private final Validator validator;
  private final CommentService commentService;

  public CommentRestController(final Validator validator, final CommentService commentService) {
    this.validator = validator;
    this.commentService = commentService;
  }

  @Timed
  @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/{cardId}")
  public ResponseEntity<List<Comment>> findbyCard(@PathVariable("cardId") String id) {
    if (!Features.TEST_TOGGLE.isActive()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok()
      .contentType(APPLICATION_JSON)
      .cacheControl(maxAge(1, HOURS).cachePublic())
      .varyBy(ACCEPT)
      .body(commentService.findByCard(id));
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Comment> create(@RequestBody final Comment comment, final Errors errors) {
    final Comment sanitizedComment = comment.sanitize();
    validator.validate(sanitizedComment, errors);
    if (errors.hasErrors()) {
      throw badRequest(errors);
    }

    final Comment createdComment = commentService.create(sanitizedComment);
    return ResponseEntity.ok().contentType(APPLICATION_JSON).body(createdComment);
  }
}
