package com.happyclassifier.happyclassiferstore.store.procedures;

import com.happyclassifier.happyclassiferstore.datatypes.enums.SentenceInferDataPredictionEnum;
import com.happyclassifier.happyclassiferstore.store.procedures.abstractions.ProcedureResults;

public class SentenceInferModelProcedureResults extends ProcedureResults {
    public SentenceInferDataPredictionEnum prediction;
}
