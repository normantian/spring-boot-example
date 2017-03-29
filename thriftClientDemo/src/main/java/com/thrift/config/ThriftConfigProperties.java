package com.thrift.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by tianfei on 17/3/28.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "thrift.server")
@Component
public class ThriftConfigProperties {

    private String host;

    private int port;

    private int timeout;

    private int maxIdle;
    private int minIdle;
    private int maxTotal;

}
