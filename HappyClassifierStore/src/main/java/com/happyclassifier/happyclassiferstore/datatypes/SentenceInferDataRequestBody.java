package com.happyclassifier.happyclassiferstore.datatypes;

import com.happyclassifier.happyclassiferstore.datatypes.abstractions.InferDataRequestBody;

public class SentenceInferDataRequestBody implements InferDataRequestBody {

    // TODO: Add payload data...

    public String sentence;

    public SentenceInferDataRequestBody(String sentence) {
        this.sentence = sentence;
    }

    public SentenceInferDataRequestBody(){

    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
