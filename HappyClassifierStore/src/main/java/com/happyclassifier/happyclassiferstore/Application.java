package com.happyclassifier.happyclassiferstore;

import ai.djl.ndarray.NDList;
import com.happyclassifier.happyclassiferstore.datatypes.SentenceInferDataRequestBody;
import com.happyclassifier.happyclassiferstore.store.procedures.SentenceInferModelProcedure;
import com.happyclassifier.happyclassiferstore.store.storeservice.StoreService;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
