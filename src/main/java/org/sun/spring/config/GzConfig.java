package org.sun.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sun.spring.api.GzClient;

/**
 * Created by tianfei on 16/11/15.
 */
@Configuration
public class GzConfig {

    @Bean(name = "gzClient")
    public GzClient gzClient(){
        return new GzClient("127.0.0.1",9999,"init token "+ System.currentTimeMillis());
    }
}
