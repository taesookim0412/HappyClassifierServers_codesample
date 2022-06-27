package com.happyclassifier.happyclassiferstore.inferences.datasetloaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
public interface DatasetLoader {

    default ArrayList<String> readVocabFromFile(File file) throws IOException {
        ArrayList<String> result = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();

        while (line != null){
            result.add(line);
            line = bufferedReader.readLine();
        }

        return result;
    }
}
