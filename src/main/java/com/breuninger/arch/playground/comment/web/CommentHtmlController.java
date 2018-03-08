package com.breuninger.arch.playground.comment.web;

import com.breuninger.arch.playground.comment.service.CommentService;
import com.codahale.metrics.annotation.Timed;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

@Controller
@RequestMapping("/comments")
public class CommentHtmlController {

  private final CommentService commentService;

  public CommentHtmlController(final CommentService commentService) {
    this.commentService = commentService;
  }

  @Timed
  @GetMapping(produces = TEXT_HTML_VALUE)
  public ModelAndView findByCard(String cardId) {
    final ModelMap model = new ModelMap();
    model.put("cardId",cardId);
    model.put("comments", commentService.findByCard(cardId));
    return new ModelAndView("commentListPage.html", model);
  }
}
