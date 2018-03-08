package com.breuninger.arch.playground.comment.web;

import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.breuninger.arch.playground.comment.service.CommentService;
import com.codahale.metrics.annotation.Timed;

@Controller
@RequestMapping("/comments")
public class CommentHtmlController {

  private final CommentService commentService;

  public CommentHtmlController(final CommentService commentService) {
    this.commentService = commentService;
  }


  @Timed
  @GetMapping(produces = TEXT_HTML_VALUE, path = "/{cardId}")
  public ModelAndView findByCard(@PathVariable("cardId") String cardId) {
    final ModelMap model = new ModelMap();
    model.put("cardId",cardId);
    model.put("comments", commentService.findByCard(cardId));
    return new ModelAndView("commentListPage.html", model);
  }

}
