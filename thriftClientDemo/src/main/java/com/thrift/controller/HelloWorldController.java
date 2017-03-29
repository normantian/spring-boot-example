package com.thrift.controller;

import com.thrift.service.HelloWorldServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianfei on 17/3/29.
 */
@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @Autowired
    private HelloWorldServiceProvider helloWorldServiceProvider;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<String> getHello(@RequestParam("username") String username){
        String result = helloWorldServiceProvider.sayHello(username);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
