package com.breuninger.arch.playground.cards.domain;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.jongo.marshall.jackson.oid.MongoId;

import com.breuninger.arch.playground.common.util.SanitizingUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder(toBuilder = true)
@AllArgsConstructor
@Value
public class Card {
    @MongoId
    private String id;
    private String title;

    private String receiverFirstName;
    private String receiverSurname;
    private String senderFirstName;
    private String senderSurname;

  public Card sanitize() {
    return this;
  }
}
