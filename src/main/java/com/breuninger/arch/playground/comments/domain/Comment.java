package com.breuninger.arch.playground.comments.domain;

import com.breuninger.arch.playground.common.util.SanitizingUtil;
import com.breuninger.arch.playground.example.domain.Example;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.jongo.marshall.jackson.oid.MongoId;

@Builder(toBuilder = true)
@AllArgsConstructor
@Value
public class Comment {

  public static final String CARD_ID = "cardId";

  @MongoId
  private String id;

  @NotEmpty
  private String cardId;

  @NotEmpty
  private String text;

  public Comment sanitize() {
    return toBuilder().text(SanitizingUtil.sanitize(text)).build();
  }

}
