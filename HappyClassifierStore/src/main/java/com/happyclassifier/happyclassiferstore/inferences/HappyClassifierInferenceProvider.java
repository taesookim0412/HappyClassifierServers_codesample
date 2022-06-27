package com.happyclassifier.happyclassiferstore.inferences;

import ai.djl.Model;
import ai.djl.inference.Predictor;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.NDManager;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;
import com.happyclassifier.happyclassiferstore.inferences.abstractions.RealTimeInferenceProvider;
import com.happyclassifier.happyclassiferstore.inferences.preprocessors.VocabPreprocessorFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.happyclassifier.happyclassiferstore.Utilities.InferenceMath.argMax;

@Component
public class HappyClassifierInferenceProvider extends RealTimeInferenceProvider {
    private final Model model;

    Translator<NDList, NDList> translator;

    VocabPreprocessorFactory vocabPreprocessorFactory;

    public HappyClassifierInferenceProvider() throws IOException {
        // load model here
        this.model = this.loadModel("pytorch_joy_and_anger_model_torchscript", "Models/pytorch_joy_and_anger_model_torchscript.pt");
        this.translator = this.createTranslator();
        this.vocabPreprocessorFactory = new VocabPreprocessorFactory("Models/joy_and_anger_vocab.txt");
    }


    public int predict(String input){
        //Preprocess the input

        try (Predictor<NDList, NDList> predictor = this.model.newPredictor(this.translator)){
            try (NDManager manager = NDManager.newBaseManager()) {


                NDList prediction = predictor.predict(vocabProcess(input, manager));
                NDArray data = prediction.get(0);
                float[] result = data.toFloatArray();

                return argMax(result);
            }
        } catch (TranslateException e) {
            // TODO: Handle TranslateError
            throw new RuntimeException(e);
        }
    }

    private NDList vocabProcess(String input, NDManager ndManager){
        Integer[] processedInput = this.vocabPreprocessorFactory.convertInputToProcessedInput(input);

        // TODO: Resolve this cast ( is it possible? )
        NDArray texts = ndManager.create(Arrays.stream(processedInput).mapToInt(Integer::intValue).toArray());

        // Currently only supports one sentence input.
        NDArray offsets = ndManager.create(0);

        ArrayList<NDArray> ndArrays = new ArrayList<NDArray>();
        ndArrays.add(texts);
        ndArrays.add(offsets);

        return new NDList(ndArrays);
        //return new NDList(texts, offsets);
    }
}
