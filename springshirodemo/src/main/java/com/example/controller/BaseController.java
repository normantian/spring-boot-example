package com.example.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


import org.apache.shiro.mgt.SecurityManager;

/**
 * Created by tianfei on 17/3/17.
 */
@Component
public class BaseController {
    @Autowired
    private SecurityManager securityManager;

    @PostConstruct
    private void initStaticSecurityManager() {
        SecurityUtils.setSecurityManager(securityManager);
    }
}
