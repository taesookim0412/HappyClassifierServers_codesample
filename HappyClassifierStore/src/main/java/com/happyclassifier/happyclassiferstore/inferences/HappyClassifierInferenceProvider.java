package com.happyclassifier.happyclassiferstore.inferences;

import ai.djl.Device;
import ai.djl.MalformedModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.NDManager;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.translate.TranslateException;
import com.happyclassifier.happyclassiferstore.Application;
import com.happyclassifier.happyclassiferstore.inferences.abstractions.RealTimeInferenceProvider;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.happyclassifier.happyclassiferstore.Utilities.ResourceUtils.getResourcePathFromFileName;

@Component
public class HappyClassifierInferenceProvider extends RealTimeInferenceProvider {
    private ZooModel<NDList, NDList> model = this.loadModel();
    public HappyClassifierInferenceProvider(){
        // load model here
    }

    private ZooModel<NDList, NDList> loadModel(){
        Criteria<NDList, NDList> criteria = Criteria.builder()
                .setTypes(NDList.class, NDList.class)
                .optModelPath(getResourcePathFromFileName("Models/pytorch_joy_and_anger_model_torchscript.pt"))
                .optModelName("pytorch_joy_and_anger_model_torchscript.pt")
                .optDevice(Device.cpu())
                .build();
        try (ZooModel<NDList, NDList> model = criteria.loadModel()){
            this.model = model;
        } catch (ModelNotFoundException e) {
            //TODO: Handle all load errors.
            throw new RuntimeException(e);
        } catch (MalformedModelException e) {
            // Some fatal error that should be handled
            throw new RuntimeException(e);
        } catch (IOException e) {
            // Some fatal error that should be handled
            throw new RuntimeException(e);
        }
        return this.model;
    }

    public NDList predict(String input){
        //Preprocess the input

        try (Predictor<NDList, NDList> predictor = this.model.newPredictor()){
            try (NDManager manager = NDManager.newBaseManager()){
                return predictor.predict(vocabProcess(input, manager));
            }
        } catch (TranslateException e) {
            // TODO: Handle TranslateError
            throw new RuntimeException(e);
        }
    }

    private NDList vocabProcess(String input, NDManager ndManager){
        // Not yet implemented the vocab pipeline and tokenizer..
        // TODO: Implement the string pre-processor
        return new NDList(ndManager.create(new int[]{2, 1, 3}));
    }
}
