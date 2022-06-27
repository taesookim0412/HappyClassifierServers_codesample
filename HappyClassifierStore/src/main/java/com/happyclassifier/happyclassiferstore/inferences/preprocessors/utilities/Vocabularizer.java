package com.happyclassifier.happyclassiferstore.inferences.preprocessors.utilities;

import java.util.*;

public interface Vocabularizer {

    default HashMap<String, Integer> buildVocabFromTokenizedSentencesOptimized(String[][] normalizedSentences, List<String> specials){
        HashMap<String, Integer> wordFrequencies = new HashMap();

        HashSet<String> specialsSet = new HashSet(specials);

        int maxFrequency = -1;

        for (String[] sentence : normalizedSentences){
            for (String word : sentence){
                if (!specialsSet.contains(word)){

                    int frequency = wordFrequencies.getOrDefault(word, 0) + 1;
                    wordFrequencies.put(word, frequency);

                    if (frequency > maxFrequency){
                        maxFrequency = frequency;
                    }
                }
            }
        }

        int biggestFrequencyAfter = maxFrequency + 1;

        for (int i = specials.size() - 1; i > -1; i--) {
            wordFrequencies.put(specials.get(i), biggestFrequencyAfter);
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
