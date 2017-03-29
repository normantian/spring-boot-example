package com.example.service.impl;

import com.example.HelloWorldService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by tianfei on 17/3/21.
 */
@Service("helloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService.Iface {
    @Override
    public String sayHello(String username) throws TException {

        return "hi " + username +" welcome to thrift world.";
    }
}
