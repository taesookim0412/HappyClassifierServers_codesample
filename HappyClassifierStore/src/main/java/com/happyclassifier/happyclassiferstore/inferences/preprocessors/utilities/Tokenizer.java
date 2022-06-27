package com.happyclassifier.happyclassiferstore.inferences.preprocessors.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public interface Tokenizer {

    default HashMap<Pattern, String> initializeTokenizerMap(){
        HashMap<String, String> patternsMap = new HashMap();
        // Put spaces before and after '
        patternsMap.put("\\'", " ' ");
        // Put spaces before and after "
        patternsMap.put("\\\"", "");
        // Put spaces before and after .
        patternsMap.put("\\.", " . ");
        // Replace html line breaks with empty space.
        patternsMap.put("<br />", " ");
        // Put spaces before and after , --> Question, why doesn't this have a backslash?
        patternsMap.put(",", " , ");
        // Put spaces before and after (
        patternsMap.put("\\(", " ( ");
        // Put spaces before and after )
        patternsMap.put("\\)", " ) ");
        // Put spaces before and after !
        patternsMap.put("\\!", " ! ");
        // Put spaces before and after ?
        patternsMap.put("\\?", " ? ");
        // Replace ; with single space
        patternsMap.put("\\;", " ");
        // Replace : with single space
        patternsMap.put("\\:", " ");
        // Replace spaces with single spaces
        patternsMap.put("\\s+", " ");

        HashMap<Pattern, String> tokenizerMap = new HashMap();
        for (Map.Entry<String, String> patternSet : patternsMap.entrySet()){
            tokenizerMap.put(Pattern.compile(patternSet.getKey()), patternSet.getValue());
        }
        return tokenizerMap;
    }

    /** Uses regex to reconstruct a sentence.
     */
    default String[] basic_english_normalize(String sentence, HashMap<Pattern, String> tokenizerMap){
        String line = sentence.toLowerCase();
        for (Map.Entry<Pattern, String> replacementsSet : tokenizerMap.entrySet()){
            line = replacementsSet.getKey().matcher(line).replaceAll(replacementsSet.getValue());
        }
        return line.split(" ");
    }

    default String[][] tokenizeDatasetVocabulary(ArrayList<String> datasetLines, HashMap<Pattern, String> tokenizerMap){
        String[][] result = new String[datasetLines.size()][];
        for (int i = 0; i < datasetLines.size(); i++){
            result[i] = basic_english_normalize(datasetLines.get(i), tokenizerMap);
        }
        return result;
    }

}
