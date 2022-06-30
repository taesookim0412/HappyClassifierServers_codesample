package com.happyclassifier.happyclassiferstore;

import com.happyclassifier.happyclassiferstore.datatypes.SentenceInferDataRequestBody;
import com.happyclassifier.happyclassiferstore.datatypes.abstractions.InferDataRequestBody;
import com.happyclassifier.happyclassiferstore.models.Sentence;
import com.happyclassifier.happyclassiferstore.repositories.SentenceRepository;
import com.happyclassifier.happyclassiferstore.store.procedures.SentenceInferModelProcedure;
import com.happyclassifier.happyclassiferstore.store.procedures.SentenceInferModelProcedureResults;
import com.happyclassifier.happyclassiferstore.store.procedures.abstractions.Procedure;
import com.happyclassifier.happyclassiferstore.store.storeservice.StoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @GetMapping("find")
    public Mono<Sentence> find(@RequestParam String phrase){
        //return this.sentenceRepository.findAll();
        Mono<Sentence> data = this.sentenceRepository.findSentenceByPhraseExact(phrase);
        return data;
    }

    @PostMapping("insert")
    public Mono<Sentence> insert(@RequestBody SentenceInferDataRequestBody data){
        Sentence newSentence = new Sentence(data.getSentence());
        Mono<Sentence> result = this.sentenceRepository.save(newSentence);
        return result;
    }

    @PostMapping("infer")
    public Mono<SentenceInferModelProcedureResults> infer(@RequestBody SentenceInferDataRequestBody data){
        SentenceInferModelProcedure procedure = new SentenceInferModelProcedure(data);
        // check Redis cache

        // check Store

        // infer data

        // store and cache
        SentenceInferModelProcedureResults procedureResults = (SentenceInferModelProcedureResults) this.storeService.call(procedure);

        return Mono.just(procedureResults);
    }
}