package com.thrift.service.impl;

import com.thrift.generated.HelloWorldService;
import com.thrift.service.HelloWorldServiceProvider;
import com.wealoha.thrift.ThriftClient;
import com.wealoha.thrift.ThriftClientPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tianfei on 17/3/29.
 */
@Slf4j
@Service("helloWorldServiceProvider")
public class HelloWorldServiceProviderImpl implements HelloWorldServiceProvider {

    @Autowired
    private ThriftClientPool<HelloWorldService.Client> thriftClientPool;

    @Override
    public String sayHello(String username) {
        String result = "";
        try(ThriftClient<HelloWorldService.Client> client = thriftClientPool.getClient()) {
            HelloWorldService.Iface iFace = client.iFace();
            result = iFace.sayHello(username);
            log.info("get thrift server response: {}",result );
            //finish must be called at last
            client.finish();
        } catch (TException ex){
            ex.printStackTrace();
            log.error("call say hello failed");
            result = "";
        }
        return result;
//        try {
//            HelloWorldService.Iface iFace = thriftClientPool.iface();
//            result = iFace.sayHello(username);
//            log.info("get thrift server response: {}",result );
//        } catch (TException ex){
//            ex.printStackTrace();
//            log.error("call say hello failed");
//            result = "";
//        }
//        return result;

    }
}
