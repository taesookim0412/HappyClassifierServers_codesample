package com.happyclassifier.happyclassiferstore.inferences.preprocessors.abstractions;

import java.util.*;

public interface Vocabularizer {

    default HashMap<String, Integer> buildVocabFromTokenizedSentencesOptimized(String[][] normalizedSentences, List<String> specials){
        HashMap<String, Integer> wordFrequencies = new HashMap();

        HashSet<String> specialsSet = new HashSet(specials);

        int maxFrequency = -1;

        for (String[] sentence : normalizedSentences){
            for (String word : sentence){
                if (!specialsSet.contains(word)){
                    wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
                    maxFrequency = Math.max(maxFrequency, wordFrequencies.get(word));
                }
            }
        }

        int biggestFrequencyAfter = maxFrequency + 1;

        for (String special : specials){
            wordFrequencies.put(special, biggestFrequencyAfter);
            biggestFrequencyAfter += 1;
        }

        ArrayList<Map.Entry<String, Integer>> sortedFrequencies = new ArrayList<Map.Entry<String, Integer>>();

        sortedFrequencies.addAll(wordFrequencies.entrySet());

        sortedFrequencies.sort((o1, o2) -> {
            int cmp1 = o2.getValue().compareTo(o1.getValue());
            if (cmp1 != 0){
                return cmp1;
            }
            return o1.getKey().compareTo(o2.getKey());
        });

        HashMap<String, Integer> result = new HashMap();

        for (int i = 0; i < sortedFrequencies.size(); i++){
            result.put(sortedFrequencies.get(i).getKey(), i);
        }

        return result;
    }
}
