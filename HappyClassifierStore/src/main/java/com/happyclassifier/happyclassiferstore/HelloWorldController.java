package com.happyclassifier.happyclassiferstore;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/story")
    public String index(){
        return "Hello, world!";
    }
}
