package com.chardip.tinyUrl.controller;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Hashtable;

@RestController
@RequestMapping("/tinyUrl")
public class TinyUrlController {

    Hashtable<String, String> hashtable = new Hashtable<>();

    @PostMapping
    public String createTinyUrl(@RequestBody String actualUrl){
        UrlValidator urlValidator = new UrlValidator(
                new String[] {"http","https"}
        );
        if(urlValidator.isValid(actualUrl)){
            String id = Hashing.murmur3_32_fixed().hashString(actualUrl, StandardCharsets.UTF_8).toString();
            System.out.println("Id generated: "+ id);
            hashtable.put(id,actualUrl);
            return id;
        }
        throw new RuntimeException("Invalid Url");
    }

    @GetMapping("/{id}")
    public String getUrl(@PathVariable String id){
        String url = hashtable.get(id);
        return url;
    }
}
