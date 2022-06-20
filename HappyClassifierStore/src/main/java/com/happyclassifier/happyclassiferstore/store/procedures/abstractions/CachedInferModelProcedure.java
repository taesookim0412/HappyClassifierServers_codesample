package com.happyclassifier.happyclassiferstore.store.procedures.abstractions;

import com.happyclassifier.happyclassiferstore.datatypes.abstractions.InferDataRequestBody;
import com.happyclassifier.happyclassiferstore.inferences.HappyClassifierInferenceProvider;
import com.happyclassifier.happyclassiferstore.inferences.abstractions.RealTimeInferenceProvider;
import org.bytedeco.javacpp.annotation.Virtual;


public abstract class CachedInferModelProcedure<T extends RealTimeInferenceProvider, S extends InferDataRequestBody> extends Procedure<T> {

    protected S inferDataBody;

    public CachedInferModelProcedure(S inferDataBody) {
        this.inferDataBody = inferDataBody;
    }

    public void tryGetFromRedis(){
        ///Not yet implemented...
    }

    public abstract ProcedureResults applyProcedure(T provider);

    protected S getInferDataBody(){
        return this.inferDataBody;
    }
}
