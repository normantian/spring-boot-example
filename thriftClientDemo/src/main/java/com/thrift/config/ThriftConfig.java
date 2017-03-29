package com.thrift.config;

import com.thrift.generated.HelloWorldService;
import com.wealoha.thrift.PoolConfig;
import com.wealoha.thrift.ServiceInfo;
import com.wealoha.thrift.ThriftClientFactory;
import com.wealoha.thrift.ThriftClientPool;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * Created by tianfei on 17/3/29.
 */
@Configuration
//@EnableConfigurationProperties(ThriftConfigProperties.class)
public class ThriftConfig {
    @Autowired
    private ThriftConfigProperties properties;

    public PoolConfig getPoolConfig(){
        PoolConfig poolConfig = new PoolConfig();
        poolConfig.setTimeout(properties.getTimeout());
        poolConfig.setMaxIdle(properties.getMaxIdle());
        poolConfig.setMaxTotal(properties.getMaxTotal());
        poolConfig.setMinIdle(properties.getMinIdle());
        return poolConfig;
    }

    public ServiceInfo getServiceInfo(){
        return new ServiceInfo(properties.getHost(),properties.getPort());
    }

    public ThriftClientFactory thriftClientFactory(){
        return new ThriftClientFactory(){

            @Override
            public TServiceClient createClient(TTransport transport) {
                return new HelloWorldService.Client(new TCompactProtocol(new TFramedTransport(transport)));
            }

        };
    }

    @Bean(name = "thriftClientPool")
    public ThriftClientPool<HelloWorldService.Client> getThriftClientPool(){
        ThriftClientPool<HelloWorldService.Client> pool =
                new ThriftClientPool<>(Collections.singletonList(getServiceInfo()),thriftClientFactory(),getPoolConfig());
        return pool;
    }
}
