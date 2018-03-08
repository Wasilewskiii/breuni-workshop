package com.breuninger.arch.playground.cards.service;

import com.breuninger.arch.playground.cards.domain.Card;
import com.breuninger.arch.playground.cards.domain.CardRepository;
import com.breuninger.arch.playground.example.domain.ExampleRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(final CardRepository cardRepository) {
      this.cardRepository = cardRepository;
    }

    public Card create(final Card card) {
      final String cardId = Math.random() + "";

      return cardRepository.create(new Card(cardId, card.getTitle(),
        card.getSenderFirstName(), card.getSenderSurname(),
        card.getReceiverFirstName(), card.getReceiverSurname()));
    }

}
