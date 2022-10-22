package com.chardip.tinyUrl.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.JedisClientConfigurationBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisSocketConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class AppConfig {

    public static StatefulRedisConnection<String, String> connect() {
        String URL = System.getenv("REDIS_URL");
        RedisURI redisURI = RedisURI.create("rediss://:p358779ee45a2e217fb0d98b3142530b8a17328f6509fd19f2162f921ca3e8346@ec2-34-236-186-12.compute-1.amazonaws.com:15830");
        redisURI.setVerifyPeer(false);

        RedisClient redisClient = RedisClient.create(redisURI);
        return redisClient.connect();
    }

    /*@Bean
    public LettuceConnectionFactory redisStandAloneConnectionFactory() {
        return new LettuceConnectionFactory(new Red("rediss://:p358779ee45a2e217fb0d98b3142530b8a17328f6509fd19f2162f921ca3e8346@ec2-34-236-186-12.compute-1.amazonaws.com", 15830));
    }



    @Bean
    public RedisTemplate<String, String> redisTemplateStandAlone(@Qualifier("redisStandAloneConnectionFactory")LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }*/

/*    @Bean
    public LettuceClientConfigurationBuilderCustomizer lettuceClientConfigurationBuilderCustomizer(){
        return clientConfigurationBuilder -> {
            if(clientConfigurationBuilder.build().isUseSsl()){
                clientConfigurationBuilder.useSsl().disablePeerVerification();
            }
        };
    }*/

    /*@Bean
    JedisConnectionFactory jedisConnectionFactory(){

        JedisConnectionFactory jedisConnectionFactory =  new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("rediss://:p358779ee45a2e217fb0d98b3142530b8a17328f6509fd19f2162f921ca3e8346@ec2-34-236-186-12.compute-1.amazonaws.com");
        jedisConnectionFactory.setPort(15830);
        return jedisConnectionFactory;
    }*/

/*    @Bean
    public RedisTemplate<String, String> redisTemplate(){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }*/

}
