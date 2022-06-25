package com.happyclassifier.happyclassiferstore;

import com.happyclassifier.happyclassiferstore.datatypes.SentenceInferDataRequestBody;
import com.happyclassifier.happyclassiferstore.store.procedures.SentenceInferModelProcedure;
import com.happyclassifier.happyclassiferstore.store.storeservice.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    StoreService storeService;

    @GetMapping("helloworld")
    private void shadyTestFunction(){
        String mockInput = "Hello, world!";
        SentenceInferModelProcedure shadyProcedure = new SentenceInferModelProcedure(new SentenceInferDataRequestBody(mockInput));
        this.storeService.call(shadyProcedure);
    }

    @GetMapping("/story")
    public String index(){
        return "Hello, world!";
    }
}
