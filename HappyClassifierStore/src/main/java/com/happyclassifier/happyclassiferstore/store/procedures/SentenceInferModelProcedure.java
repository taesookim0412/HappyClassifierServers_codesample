package com.happyclassifier.happyclassiferstore.store.procedures;

import com.happyclassifier.happyclassiferstore.datatypes.SentenceInferDataRequestBody;
import com.happyclassifier.happyclassiferstore.datatypes.enums.ResultState;
import com.happyclassifier.happyclassiferstore.datatypes.enums.SentenceInferDataPredictionEnum;
import com.happyclassifier.happyclassiferstore.inferences.HappyClassifierInferenceProvider;
import com.happyclassifier.happyclassiferstore.store.procedures.abstractions.CachedInferModelProcedure;


public class SentenceInferModelProcedure extends CachedInferModelProcedure<HappyClassifierInferenceProvider, SentenceInferDataRequestBody> {
    public SentenceInferModelProcedure(SentenceInferDataRequestBody sentenceInferData) {
        super(sentenceInferData);
    }

    protected SentenceInferDataRequestBody getInferDataBody() {
        return super.getInferRequestBody();
    }

    public SentenceInferModelProcedureResults applyProcedure(HappyClassifierInferenceProvider provider) {
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

        return procedureResults;

    }
}

