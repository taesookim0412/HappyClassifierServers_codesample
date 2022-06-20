package com.happyclassifier.happyclassiferstore.store.procedures.abstractions;

import com.happyclassifier.happyclassiferstore.datatypes.abstractions.InferResponseBody;
import com.happyclassifier.happyclassiferstore.datatypes.enums.ResultState;

public abstract class ProcedureResults {
    public ResultState state;
    public InferResponseBody inferResponseBody;
}
