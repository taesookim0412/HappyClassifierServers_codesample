package com.happyclassifier.happyclassiferstore.store.procedures.abstractions;

import com.happyclassifier.happyclassiferstore.datatypes.abstractions.InferDataRequestBody;
import com.happyclassifier.happyclassiferstore.inferences.HappyClassifierInferenceProvider;
import com.happyclassifier.happyclassiferstore.inferences.abstractions.RealTimeInferenceProvider;


public abstract class CachedInferModelProcedure<T extends RealTimeInferenceProvider, S extends InferDataRequestBody> extends Procedure<T> {

    protected S inferRequestBody;

    public CachedInferModelProcedure(S inferRequestBody) {
        this.inferRequestBody = inferRequestBody;
    }

    public void tryGetFromRedis(){
        ///Not yet implemented...
    }

    public abstract ProcedureResults applyProcedure(T provider);

    protected S getInferRequestBody(){
        return this.inferRequestBody;
    }
}
