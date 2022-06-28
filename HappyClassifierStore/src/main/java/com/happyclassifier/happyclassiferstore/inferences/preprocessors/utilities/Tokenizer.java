package com.happyclassifier.happyclassiferstore.inferences.preprocessors.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public interface Tokenizer {

    default ArrayList<ArrayList<Object>> initializeTokenizerMap() {
        String[][] patternsList = new String[][]{
                // Put spaces before and after '
                new String[]{"'", " ' "},
                // Remove "
                new String[]{"\"", ""},
                // Put spaces before and after .
                new String[]{"\\.", " . "},
                // Replace html line breaks with empty space.
                new String[]{"<br />", " "},
                // Put spaces before and after , --> Question, why doesn't this have a backslash?
                new String[]{",", " , "},
                // Put spaces before and after (
                new String[]{"\\(", " \\( " },
                // Put spaces before and after )
                new String[]{"\\)", " \\) "},
                // Put spaces before and after !
                new String[]{"!", " ! "},
                // Put spaces before and after ?
                new String[]{"\\?", " ? "},
                // Replace ; with single space
                new String[]{";", " "},
                // Replace : with single space
                new String[]{":", " "},
                // Replace spaces with single spaces
                new String[]{"\\s+", " "}
        };

        ArrayList<ArrayList<Object>> tokenizerArray = new ArrayList<ArrayList<Object>>(patternsList.length);
        for (int i = 0; i < patternsList.length; i++){
            ArrayList<Object> entry = new ArrayList<Object>(2);
            entry.add(Pattern.compile(patternsList[i][0]));
            entry.add(patternsList[i][1]);
            tokenizerArray.add(entry);
        }
        return tokenizerArray;
    }

    /**
     * Uses regex to reconstruct a sentence.
     */
    default String[] basic_english_normalize(String sentence, ArrayList<ArrayList<Object>> tokenizerArray) {
        String line = sentence.toLowerCase();
        for (ArrayList<Object> patternToReplacement : tokenizerArray) {
            Pattern pattern = (Pattern) patternToReplacement.get(0);
            String replacement = (String) patternToReplacement.get(1);
            line = pattern.matcher(line).replaceAll(replacement);
        }
        return line.split(" ");
    }

    default String[][] tokenizeDatasetVocabulary(ArrayList<String> datasetLines, ArrayList<ArrayList<Object>> tokenizerArray) {
        String[][] result = new String[datasetLines.size()][];
        for (int i = 0; i < datasetLines.size(); i++) {
            result[i] = basic_english_normalize(datasetLines.get(i), tokenizerArray);
        }
        return result;
    }

}
