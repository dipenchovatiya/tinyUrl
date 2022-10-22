package com.chardip.tinyUrl.controller;

import com.chardip.tinyUrl.config.AppConfig;
import com.google.common.hash.Hashing;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
            StatefulRedisConnection<String, String> connect = AppConfig.connect();
            System.out.println("Connection Status: " + connect.isOpen());
            RedisCommands redisCommands = connect.sync();
            redisCommands.set(id,actualUrl);
            return id;
        }
        throw new RuntimeException("Invalid Url");
    }

    @GetMapping("/{id}")
    public String getUrl(@PathVariable String id){
        StatefulRedisConnection<String, String> connect = AppConfig.connect();
        System.out.println("Connection Status: " + connect.isOpen());
        RedisCommands redisCommands = connect.sync();
        return (String) redisCommands.get(id);
    }
}
