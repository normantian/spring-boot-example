package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianfei on 17/3/17.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping(value = "hello")
    public String hello(){
        return "hello";
    }
}
