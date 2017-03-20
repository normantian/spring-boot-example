package com.example.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.apache.shiro.mgt.SecurityManager;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Created by tianfei on 17/3/14.
 */
@RestController
@RequestMapping("/shiroDemo")
public class MyShiroController {//extends BaseController {

//    @Autowired
//    private SecurityManager securityManager;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String username, String password){
        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("jill.coder", "password");
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        subject.login(token);
        return username + " login success";
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value="logout",method =RequestMethod.GET)
    @ResponseBody
    public String logout(){
//        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        try {
            //退出
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return username + " logout.";
    }

    @RequestMapping(value = "/test",
            method = RequestMethod.GET,
            produces = {APPLICATION_JSON_UTF8_VALUE}
    )
//    @RequiresPermissions(value = "write")
    @RequiresRoles("admin")
    public String hello() {
        return "hello " + SecurityUtils.getSubject().getPrincipal();
    }

}
