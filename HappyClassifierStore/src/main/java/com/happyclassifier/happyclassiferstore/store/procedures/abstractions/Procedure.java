package com.happyclassifier.happyclassiferstore.store.procedures.abstractions;

import com.happyclassifier.happyclassiferstore.inferences.abstractions.RealTimeInferenceProvider;
import org.bytedeco.javacpp.annotation.Virtual;

public abstract class Procedure<T extends RealTimeInferenceProvider> {

    public abstract ProcedureResults applyProcedure(T provider);
}
