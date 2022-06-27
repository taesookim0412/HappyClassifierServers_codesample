package com.happyclassifier.happyclassiferstore.inferences.preprocessors;

import com.happyclassifier.happyclassiferstore.inferences.datasetloaders.DatasetLoader;
import com.happyclassifier.happyclassiferstore.inferences.preprocessors.utilities.Tokenizer;
import com.happyclassifier.happyclassiferstore.inferences.preprocessors.utilities.Vocabularizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

import static com.happyclassifier.happyclassiferstore.Utilities.ResourceUtils.getResourceFileFromFileName;

public class VocabPreprocessorFactory implements Vocabularizer, Tokenizer, DatasetLoader {
    HashMap<Pattern, String> tokenizerMap;

    public final HashMap<String, Integer> vocabMap;

    public VocabPreprocessorFactory(String vocabFileName) throws IOException {
        // Compile regex replacements and create our map.
        this.tokenizerMap = this.initializeTokenizerMap();
        // Read our dataset as an ArrayList of strings.
        ArrayList<String> datasetLines = this.readVocabFromFile(getResourceFileFromFileName(vocabFileName));
        // Convert our vocabulary lines to tokenized strings.
        String[][] tokenizedVocabulary = this.tokenizeDatasetVocabulary(datasetLines, this.tokenizerMap);

        // Initialize our vocabulary map.
        this.vocabMap = this.buildVocabFromTokenizedSentencesOptimized(tokenizedVocabulary, Arrays.asList("<unk>"));
    }

    public String[] tokenizerPipeline(String sentence){
        return this.basic_english_normalize(sentence, this.tokenizerMap);
    }

    public Integer[] vocabPipeline(String[] tokenizedSentence){
        Integer[] result = new Integer[tokenizedSentence.length];
        for (int i = 0; i < tokenizedSentence.length; i++){
            result[i] = this.vocabMap.getOrDefault(tokenizedSentence[i], 0);
        }

        return result;
    }

    public Integer[] convertInputToProcessedInput(String input){
        // TODO: Migrate this vocab pipeline from regex to something faster.
        return this.vocabPipeline(this.tokenizerPipeline(input));
    }

}
