package com.happyclassifier.happyclassiferstore.dao;

import com.happyclassifier.happyclassiferstore.models.Sentence;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SentenceDao extends ReactiveMongoRepository<Sentence, String> {
    @Query("{ 'phrase' : ?0 }")
    Mono<Sentence> findSentenceByPhraseExact(String phrase);

}
