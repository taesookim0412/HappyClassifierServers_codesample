package com.happyclassifier.happyclassiferstore.repositories;

import com.happyclassifier.happyclassiferstore.models.Sentence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SentenceRepository extends MongoRepository<Sentence, String> {
    @Query("{ 'phrase' : ?0 }")
    List<Sentence> findSentenceByPhraseExact(String phrase);
}
