package com.happyclassifier.happyclassiferstore;

import com.happyclassifier.happyclassiferstore.models.Sentence;
import com.happyclassifier.happyclassiferstore.repositories.SentenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentenceController {

    @Autowired
    private SentenceRepository sentenceRepository;

    @PostMapping("insert")
    public Sentence insert(@RequestBody String phrase){
        System.out.println(phrase);
        Sentence sentence = new Sentence(phrase);
        this.sentenceRepository.save(sentence);
        return sentence;
    }

}
