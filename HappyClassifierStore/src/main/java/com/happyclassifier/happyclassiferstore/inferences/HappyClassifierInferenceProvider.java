package com.happyclassifier.happyclassiferstore.inferences;

import ai.djl.Device;
import ai.djl.MalformedModelException;
import ai.djl.Model;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.NDManager;
import ai.djl.ndarray.types.Shape;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;
import ai.djl.translate.TranslatorContext;
import com.happyclassifier.happyclassiferstore.Application;
import com.happyclassifier.happyclassiferstore.inferences.abstractions.RealTimeInferenceProvider;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static com.happyclassifier.happyclassiferstore.Utilities.ResourceUtils.getResourcePathFromFileName;

@Component
public class HappyClassifierInferenceProvider extends RealTimeInferenceProvider {
    private Model model = this.loadModel();

    Translator<NDList, NDList> translator = new Translator<NDList, NDList>() {
        @Override
        public NDList processOutput(TranslatorContext translatorContext, NDList ndList) throws Exception {
            return ndList;
        }

        @Override
        public NDList processInput(TranslatorContext translatorContext, NDList ndArrays) throws Exception {
            return ndArrays;
        }
    };

    public HappyClassifierInferenceProvider(){
        // load model here
    }

    private Model loadModel(){
        Model model = Model.newInstance("pytorch_joy_and_anger_model_torchscript");
        try{
            model.load(getResourcePathFromFileName("Models/pytorch_joy_and_anger_model_torchscript.pt"));
        } catch (MalformedModelException e) {
            // TODO: Implement errors-handling.
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO: Implement errors-handling.{
            throw new RuntimeException(e);
        }
        return model;
    }



    public float[] predict(String input){
        //Preprocess the input

        try (Predictor<NDList, NDList> predictor = this.model.newPredictor(this.translator)){
            try (NDManager manager = NDManager.newBaseManager()){
                NDList prediction = predictor.predict(vocabProcess(input, manager));
                NDArray data = prediction.get(0);
                float[] result = data.toFloatArray();
                return result;
            }
        } catch (TranslateException e) {
            // TODO: Handle TranslateError
            throw new RuntimeException(e);
        }
    }

    private NDList vocabProcess(String input, NDManager ndManager){
        // Not yet implemented the vocab pipeline and tokenizer..
        // TODO: Implement the string pre-processor
        //input texts, offsets
        NDArray texts = ndManager.create(2);
        NDArray offsets = ndManager.create(0);


        return new NDList(texts, offsets);
    }
}
