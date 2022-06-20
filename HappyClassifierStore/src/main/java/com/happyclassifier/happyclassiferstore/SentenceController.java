package com.happyclassifier.happyclassiferstore;

import com.happyclassifier.happyclassiferstore.datatypes.SentenceInferDataRequestBody;
import com.happyclassifier.happyclassiferstore.models.Sentence;
import com.happyclassifier.happyclassiferstore.repositories.SentenceRepository;
import com.happyclassifier.happyclassiferstore.store.procedures.SentenceInferModelProcedure;
import com.happyclassifier.happyclassiferstore.store.procedures.abstractions.Procedure;
import com.happyclassifier.happyclassiferstore.store.storeservice.StoreService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentenceController {

    private final SentenceRepository sentenceRepository;
    private final StoreService storeService;

    public SentenceController(
            SentenceRepository sentenceRepository,
            StoreService storeService
                              ){
        this.sentenceRepository = sentenceRepository;
        this.storeService = storeService;
    }

    @PostMapping("insert")
    public Sentence insert(@RequestBody String phrase){
        System.out.println(phrase);
        Sentence sentence = new Sentence(phrase);
        this.sentenceRepository.save(sentence);
        return sentence;
    }

    @PostMapping("infer")
    public Sentence infer(@RequestBody SentenceInferDataRequestBody sentenceInferData){
        SentenceInferModelProcedure procedure = new SentenceInferModelProcedure(sentenceInferData);
        // check Redis cache

        // check Store

        // infer data

        // store and cache
        this.storeService.call(procedure);

        return new Sentence("Not fully implemented.");
    }

}
