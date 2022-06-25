package com.happyclassifier.happyclassiferstore.store.procedures;

import ai.djl.ndarray.NDList;
import com.happyclassifier.happyclassiferstore.datatypes.SentenceInferDataRequestBody;
import com.happyclassifier.happyclassiferstore.inferences.HappyClassifierInferenceProvider;
import com.happyclassifier.happyclassiferstore.store.procedures.abstractions.CachedInferModelProcedure;
import com.happyclassifier.happyclassiferstore.store.procedures.abstractions.ProcedureResults;

import java.util.Arrays;

public class SentenceInferModelProcedure extends CachedInferModelProcedure<HappyClassifierInferenceProvider, SentenceInferDataRequestBody> {
    public SentenceInferModelProcedure(SentenceInferDataRequestBody sentenceInferData) {
        super(sentenceInferData);
    }

    protected SentenceInferDataRequestBody getInferDataBody() {
        return super.getInferRequestBody();
    }

    public ProcedureResults applyProcedure(HappyClassifierInferenceProvider provider) {
        float[] results = provider.predict(this.getInferDataBody().sentence);
        System.out.println(Arrays.toString(results));


        // ahh don't want to mess with the ProcedureResults return type
        return null;

    }
}

