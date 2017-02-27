package org.sun.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sun.spring.entity.ThreadPoolBean;

/**
 * Created by tianfei on 17/2/27.
 */
@Configuration
@EnableConfigurationProperties(MyWebServerConfigurationProperties.class) //通过这个注解, 将MyWebServerConfigurationProperties这个类的配置到上下文环境中,本类中使用的@Autowired注解注入才能生效
public class MyWebServerConfiguration {
    @Autowired
    private MyWebServerConfigurationProperties properties;
    /**
     *下面就可以引用MyWebServerConfigurationProperties类       里的配置了
     */
    public void setMyconfig() {
        int port = properties.getPort();
        // ...........
    }

    @Bean
    public ThreadPoolBean getThreadBean(){
        MyWebServerConfigurationProperties.ThreadPool threadPool = properties.getThreadPool();
        ThreadPoolBean threadPoolBean = new ThreadPoolBean();
        threadPoolBean.setIdleTimeout(threadPool.getIdleTimeout());
        threadPoolBean.setMaxThreads(threadPool.getMaxThreads());
        threadPoolBean.setMinThreads(threadPool.getMinThreads());
        return threadPoolBean;
    }
}
