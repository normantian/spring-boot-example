package org.sun.spring.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Created by tianfei on 17/3/14.
 */
@RestController
@RequestMapping("/shiroDemo")
public class MyShiroController {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String username, String password){
        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("jill.coder", "password");
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        subject.login(token);
        return "success";
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value="logout",method =RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> logout(){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
            //退出
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resultMap;
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
//
//    @RequestMapping(value = "/hello",
//            method = RequestMethod.GET,
//            produces = {APPLICATION_JSON_UTF8_VALUE}
//    )
//    @RequiresPermissions("write")
//    public String hello2(){
//        return "hello " + SecurityUtils.getSubject().getPrincipal();
//    }
}
