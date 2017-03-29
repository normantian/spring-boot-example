package com.thrift.server.config;

import com.example.HelloWorldService;
import com.example.service.impl.HelloWorldServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * thrift server config
 * Created by tianfei on 17/3/28.
 */
@Configuration
@EnableConfigurationProperties(ThriftConfigProperties.class)
public class ThriftConfig {
    @Autowired
    private ThriftConfigProperties thriftConfigProperties;

    @Bean
    public HelloWorldService.Iface helloWorldService(){
        return new HelloWorldServiceImpl();
    }


    @Bean(name="thriftServerProxy")
    public ThriftServerProxy thriftServerProxy(){
        ThriftServerProxy proxy =
                new ThriftServerProxy(thriftConfigProperties, helloWorldService());
        proxy.start();
        return proxy;
    }
}
