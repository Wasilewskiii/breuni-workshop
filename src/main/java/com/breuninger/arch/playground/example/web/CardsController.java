package com.breuninger.arch.playground.example.web;

import com.breuninger.arch.playground.cards.domain.Card;
import com.breuninger.arch.playground.cards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.breuninger.arch.playground.common.domain.BadRequestException.badRequest;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/cards")
public class CardsController {

  private CardService cardService;

  @Autowired
  public CardsController(CardService service) {
    cardService = service;
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Card> create(@RequestBody final Card card, final Errors errors) {
    final Card sanitizedCard = card.sanitize();

    if (errors.hasErrors()) {
      throw badRequest(errors);
    }

    final Card createdCard = cardService.create(sanitizedCard);
    return ResponseEntity.ok().contentType(APPLICATION_JSON).body(createdCard);
  }


}
