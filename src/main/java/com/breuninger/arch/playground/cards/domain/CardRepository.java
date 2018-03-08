package com.breuninger.arch.playground.cards.domain;

import com.breuninger.arch.playground.common.domain.JongoMapper;
import com.breuninger.arch.playground.cards.domain.Card;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.otto.edison.mongo.AbstractMongoRepository;
import org.bson.Document;
import org.springframework.stereotype.Repository;

@Repository
public class CardRepository extends AbstractMongoRepository<String, Card> {

  private final MongoCollection<Document> collection;

  public CardRepository(final MongoDatabase mongoDatabase) {
    collection = mongoDatabase.getCollection("cards");
  }

  @Override
  protected MongoCollection<Document> collection() {
    return collection;
  }

  @Override
  protected String keyOf(final Card card) {
    return card.getId();
  }

  @Override
  protected Document encode(final Card card) {
    return JongoMapper.encode(card);
  }

  @Override
  protected Card decode(final Document document) {
    return JongoMapper.decode(document, Card.class);
  }

  @Override
  protected void ensureIndexes() {
    // CompletableFuture.runAsync(() -> collection().createIndex(Indexes.compoundIndex(Indexes.ascending(BOARD_ID), Indexes.descending(CREATION_DATE)), new IndexOptions().background(true)));
  }
}
