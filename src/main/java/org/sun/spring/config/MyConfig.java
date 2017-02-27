package org.sun.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianfei on 17/2/23.
 */
@Component //不加这个注解的话, 使用@Autowired 就不能注入进去了
@ConfigurationProperties(prefix = "my") // 配置文件中的前缀
public class MyConfig {
    private List<String> servers = new ArrayList<>();

    public List<String> getServers() {
        return servers;
    }

    public void setServers(List<String> servers) {
        this.servers = servers;
    }
}
