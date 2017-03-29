package com.thrift.server.config;

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

    //private String host;

    private int port;

    //private int timeout;

    private String thriftInterface; // thrift接口

    private int selectorThreadCount; //网络io线程池大小

    private int workerThreadCount; // 服务线程池大小

    private int acceptQueueSizePerThread; //默认



}
