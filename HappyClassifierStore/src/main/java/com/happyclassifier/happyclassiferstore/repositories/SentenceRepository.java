package com.happyclassifier.happyclassiferstore.repositories;

import com.happyclassifier.happyclassiferstore.dao.SentenceDao;
import com.happyclassifier.happyclassiferstore.models.Sentence;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SentenceRepository extends SentenceDao {

}