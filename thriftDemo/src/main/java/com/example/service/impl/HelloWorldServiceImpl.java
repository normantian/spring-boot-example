package com.example.service.impl;

import com.example.HelloWorldService;
import org.apache.thrift.TException;

/**
 * Created by tianfei on 17/3/21.
 */
public class HelloWorldServiceImpl implements HelloWorldService.Iface {
    @Override
    public String sayHello(String username) throws TException {

        return "hi " + username +" welcome to thrift world.";
    }
}
