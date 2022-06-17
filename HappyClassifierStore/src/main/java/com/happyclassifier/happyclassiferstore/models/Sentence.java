package com.happyclassifier.happyclassiferstore.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Sentence {
    @Id
    private String id;

    @Field("phrase")
    private String phrase;

    @Field("connotation")
    private double connotation;

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public double getConnotation() {
        return connotation;
    }

    public void setConnotation(double connotation) {
        this.connotation = connotation;
    }

    public String getId() {
        return id;
    }

    public Sentence(String phrase){
        this.phrase = phrase;
    }
}
