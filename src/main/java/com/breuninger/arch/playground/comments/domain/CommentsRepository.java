package com.breuninger.arch.playground.comments.domain;

import com.breuninger.arch.playground.common.domain.JongoMapper;
import com.breuninger.arch.playground.example.domain.Example;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import de.otto.edison.mongo.AbstractMongoRepository;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.breuninger.arch.playground.comments.domain.Comment.CARD_ID;
import static com.mongodb.client.model.Filters.eq;

@Repository
public class CommentsRepository extends AbstractMongoRepository<String, Comment> {

  private final MongoCollection<Document> collection;

  public CommentsRepository(final MongoDatabase mongoDatabase) {
    collection = mongoDatabase.getCollection("comments");
  }

  @Override
  protected MongoCollection<Document> collection() {
    return collection;
  }

  @Override
  protected String keyOf(final Comment comment) {
    return comment.getId();
  }

  @Override
  protected Document encode(final Comment comment) {
    return JongoMapper.encode(comment);
  }

  @Override
  protected Comment decode(final Document document) {
    return JongoMapper.decode(document, Comment.class);
  }


  @Override
  protected void ensureIndexes() {
    // CompletableFuture.runAsync(() -> collection().createIndex(Indexes.compoundIndex(Indexes.ascending(BOARD_ID), Indexes.descending(CREATION_DATE)), new IndexOptions().background(true)));
  }

  public List<Comment> findCommentsByCardId(String exampleCardId) {
    return collection.find(eq(CARD_ID, exampleCardId))
      .map(this::decode)
      .into(new ArrayList<>());
  }
}
