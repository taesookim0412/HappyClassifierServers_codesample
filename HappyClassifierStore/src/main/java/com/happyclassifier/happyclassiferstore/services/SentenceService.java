package com.happyclassifier.happyclassiferstore.services;

import com.happyclassifier.happyclassiferstore.models.repositories.SentenceRepository;
import org.springframework.stereotype.Service;

@Service
public class SentenceService {

    private final SentenceRepository sentenceRepository;

    public SentenceService(SentenceRepository sentenceRepository){
        this.sentenceRepository = sentenceRepository;
    }

}
