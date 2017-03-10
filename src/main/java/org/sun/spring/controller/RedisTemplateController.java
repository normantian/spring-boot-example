package org.sun.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sun.spring.dao.UserDao;
import org.sun.spring.entity.User;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Created by tianfei on 17/3/10.
 */
@RestController
@RequestMapping("/redis")
public class RedisTemplateController {
    @Autowired
    UserDao userDao;

    @RequestMapping("/set")
    public void set(@RequestParam("key") String key){
        User user = new User(1L,"wang","yunfei");
        userDao.save(user);
        userDao.stringRedisTemplateDemo("key",key);
    }

    @RequestMapping("/getStr")
    public String getStr(){
        return userDao.getString("key");
    }

    @RequestMapping(value = "/getUser",produces = APPLICATION_JSON_UTF8_VALUE )
    public User getUser(){
        return userDao.getUser();
    }
}
