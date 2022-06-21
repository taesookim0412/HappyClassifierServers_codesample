package com.happyclassifier.happyclassiferstore.inferences;

import com.happyclassifier.happyclassiferstore.inferences.abstractions.RealTimeInferenceProvider;
import org.springframework.stereotype.Component;

@Component
public class HappyClassifierInferenceProvider extends RealTimeInferenceProvider {
    public int test = 0;
    public HappyClassifierInferenceProvider(){
        for (int i = 0; i < 100; i++){
            test += i;
        }
        System.out.println("test changed");

        // load model here
    }
}
