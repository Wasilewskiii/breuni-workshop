package com.breuninger.arch.playground.comment.domain;

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
public class Comment {

  public static final String CARD_ID = "cardId";

  @MongoId
  private String id;

  @NotEmpty
  private String cardId;

  @NotEmpty
  private String text;

  private Date creationDate;
  private Date lastModificationDate;

  public Comment sanitize() {
    return toBuilder().text(SanitizingUtil.sanitize(text)).build();
  }
}
