package com.happyclassifier.happyclassiferstore.inferences.abstractions;

import ai.djl.MalformedModelException;
import ai.djl.Model;
import ai.djl.ndarray.NDList;
import ai.djl.translate.Translator;
import ai.djl.translate.TranslatorContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.happyclassifier.happyclassiferstore.Utilities.ResourceUtils.getResourcePathFromFileName;

public abstract class RealTimeInferenceProvider {
    public String model;
    protected Model loadModel(String modelName, String modelPath){
        Model model = Model.newInstance(modelName);
        try{
            model.load(getResourcePathFromFileName(modelPath));
        } catch (MalformedModelException e) {
            // TODO: Implement errors-handling.
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO: Implement errors-handling.{
            throw new RuntimeException(e);
        }
        return model;
    }
    protected Translator<NDList, NDList> createTranslator() {
        return new Translator<NDList, NDList>() {
            @Override
            public NDList processOutput(TranslatorContext translatorContext, NDList ndList) throws Exception {
                return ndList;
            }

            @Override
            public NDList processInput(TranslatorContext translatorContext, NDList ndArrays) throws Exception {
                return ndArrays;
            }
        };
    }
}
