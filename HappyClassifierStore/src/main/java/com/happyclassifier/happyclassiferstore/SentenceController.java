package com.happyclassifier.happyclassiferstore;

import com.happyclassifier.happyclassiferstore.inferences.HappyClassifierInferenceProvider;
import com.happyclassifier.happyclassiferstore.models.Sentence;
import com.happyclassifier.happyclassiferstore.repositories.SentenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentenceController {

    private final SentenceRepository sentenceRepository;
    private final HappyClassifierInferenceProvider happyClassifierInferenceProvider;

    public SentenceController(
            SentenceRepository sentenceRepository,
            HappyClassifierInferenceProvider happyClassifierInferenceProvider
                              ){
        this.sentenceRepository = sentenceRepository;
        this.happyClassifierInferenceProvider = happyClassifierInferenceProvider;

    }

    @PostMapping("insert")
    public Sentence insert(@RequestBody String phrase){
        System.out.println(phrase);
        Sentence sentence = new Sentence(phrase);
        this.sentenceRepository.save(sentence);
        return sentence;
    }

    @PostMapping("infer")
    public Sentence infer(@RequestBody String phrase){
        // check Redis cache

        // check Store

        // infer data

        // store and cache

        //
    }

}
