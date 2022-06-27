package com.happyclassifier.happyclassiferstore.store.procedures;

import ai.djl.ndarray.NDList;
import com.happyclassifier.happyclassiferstore.datatypes.SentenceInferDataRequestBody;
import com.happyclassifier.happyclassiferstore.datatypes.enums.ResultState;
import com.happyclassifier.happyclassiferstore.datatypes.enums.SentenceInferDataPredictionEnum;
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
        SentenceInferModelProcedureResults procedureResults = new SentenceInferModelProcedureResults();

        int result = provider.predict(this.getInferDataBody().sentence);

        // TODO : reverse enum lookup
        switch (result){
            case 0:
                procedureResults.state = ResultState.Success;
                procedureResults.prediction = SentenceInferDataPredictionEnum.Joy;
                break;
            case 1:
                procedureResults.state = ResultState.Success;
                procedureResults.prediction = SentenceInferDataPredictionEnum.Anger;
                break;
            default:
                procedureResults.state = ResultState.Failed;
                break;
        }
        System.out.println(procedureResults);

        // ahh don't want to mess with the ProcedureResults return type
        return procedureResults;

    }
}

