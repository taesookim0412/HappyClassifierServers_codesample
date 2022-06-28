package com.happyclassifier.happyclassiferstore.inferences.preprocessors.utilities;

import java.util.ArrayList;
import java.util.regex.Pattern;

public interface Tokenizer {

    default Object[][] initializeTokenizerArray() {
        String[][] patternsList = new String[][]{
                // Put spaces before and after '
                new String[]{"'", " ' "},
                // Remove "
                new String[]{"\"", ""},
                // Put spaces before and after .
                new String[]{"\\.", " . "},
                // Replace html line breaks with empty space.
                new String[]{"<br />", " "},
                // Put spaces before and after ,
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

        Object[][] tokenizerArray = new Object[patternsList.length][2];
        for (int i = 0; i < patternsList.length; i++){
            tokenizerArray[i][0] = Pattern.compile(patternsList[i][0]);
            tokenizerArray[i][1] = patternsList[i][1];
        }
        return tokenizerArray;
    }

    /**
     * Uses regex to reconstruct a sentence.
     */
    default String[] basic_english_normalize(String sentence, Object[][] tokenizerArray) {
        String line = sentence.toLowerCase();
        for (Object[] patternToReplacement : tokenizerArray) {
            Pattern pattern = (Pattern) patternToReplacement[0];
            String replacement = (String) patternToReplacement[1];
            line = pattern.matcher(line).replaceAll(replacement);
        }
        return line.split(" ");
    }

    default String[][] tokenizeDatasetVocabulary(ArrayList<String> datasetLines, Object[][] tokenizerArray) {
        String[][] result = new String[datasetLines.size()][];
        for (int i = 0; i < datasetLines.size(); i++) {
            result[i] = basic_english_normalize(datasetLines.get(i), tokenizerArray);
        }
        return result;
    }

}
