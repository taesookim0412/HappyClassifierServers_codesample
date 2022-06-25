package com.happyclassifier.happyclassiferstore.store.procedures;

import com.happyclassifier.happyclassiferstore.datatypes.SentenceInferDataRequestBody;
import com.happyclassifier.happyclassiferstore.inferences.HappyClassifierInferenceProvider;
import com.happyclassifier.happyclassiferstore.store.procedures.abstractions.CachedInferModelProcedure;
import com.happyclassifier.happyclassiferstore.store.procedures.abstractions.ProcedureResults;

public class SentenceInferModelProcedure extends CachedInferModelProcedure<HappyClassifierInferenceProvider, SentenceInferDataRequestBody> {
    public SentenceInferModelProcedure(SentenceInferDataRequestBody sentenceInferData) {
        super(sentenceInferData);
    }

    protected SentenceInferDataRequestBody getInferDataBody() {
        return super.getInferRequestBody();
    }

    public ProcedureResults applyProcedure(HappyClassifierInferenceProvider provider) {
        ProcedureResults results = null;
        // infer here
        return results;

    }
}

