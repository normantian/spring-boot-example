package org.sun.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tianfei on 17/3/16.
 */
@Configuration
public class TestConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public Object object(){
        System.out.println(host + ": " + port);
        return new Object();
    }
}
