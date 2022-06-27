package com.happyclassifier.happyclassiferstore.Utilities;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceUtils {
    public static Path getResourcePathFromFileName(String fileName){
        return getResourcePath(loadResource(fileName));
    }
    public static File getResourceFileFromFileName(String fileName){
        return getResourceFile(loadResource(fileName));
    }

    public static Resource loadResource(String fileName){
        return new ClassPathResource(fileName);
    }

    public static File getResourceFile(Resource resource){
        try{
            String uriPath = resource.getURI().getPath();
            File file = new File(uriPath);
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Path getResourcePath(Resource resource) {
        try{
            String uriPath = resource.getURI().getPath();
            File file = new File(uriPath);
            return Paths.get(file.getPath());
        }
        catch (IOException e) {
            // TODO: Handle URI error.
            throw new RuntimeException(e);
        }
    }
}
