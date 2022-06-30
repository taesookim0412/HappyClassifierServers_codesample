package com.happyclassifier.happyclassiferstore.services;

import com.happyclassifier.happyclassiferstore.models.Sentence;
import com.happyclassifier.happyclassiferstore.repositories.SentenceRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class SentenceService {

    private final SentenceRepository sentenceRepository;

    public SentenceService(SentenceRepository sentenceRepository){
        this.sentenceRepository = sentenceRepository;
    }

}
