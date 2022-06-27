package com.happyclassifier.happyclassiferstore.store.storeservice;

import com.happyclassifier.happyclassiferstore.inferences.HappyClassifierInferenceProvider;
import com.happyclassifier.happyclassiferstore.store.procedures.abstractions.Procedure;
import com.happyclassifier.happyclassiferstore.store.procedures.abstractions.ProcedureResults;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    private final HappyClassifierInferenceProvider modelProvider;

    public StoreService(HappyClassifierInferenceProvider modelProvider){
        this.modelProvider = modelProvider;
    }

    public ProcedureResults call(Procedure procedure){
        return procedure.applyProcedure(this.modelProvider);

    }
}
