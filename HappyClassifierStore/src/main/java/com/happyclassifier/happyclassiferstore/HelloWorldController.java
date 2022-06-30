package com.happyclassifier.happyclassiferstore;

import com.happyclassifier.happyclassiferstore.datatypes.SentenceInferDataRequestBody;
import com.happyclassifier.happyclassiferstore.store.procedures.SentenceInferModelProcedure;
import com.happyclassifier.happyclassiferstore.store.procedures.SentenceInferModelProcedureResults;
import com.happyclassifier.happyclassiferstore.store.storeservice.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloWorldController {

    @Autowired
    StoreService storeService;

    @GetMapping("helloworld")
    private Mono<SentenceInferModelProcedureResults> shadyTestFunction(String sentence){
        SentenceInferModelProcedure shadyProcedure = new SentenceInferModelProcedure(new SentenceInferDataRequestBody(sentence));
        SentenceInferModelProcedureResults results = (SentenceInferModelProcedureResults) this.storeService.call(shadyProcedure);
        return Mono.just(results);
    }
}
