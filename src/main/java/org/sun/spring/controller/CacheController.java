package org.sun.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.sun.spring.entity.Address;
import org.sun.spring.entity.User;
import org.sun.spring.service.CacheService;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

/**
 * Created by tianfei on 17/3/9.
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping(value = "/put",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public ResponseEntity<?> putCache() {
        User user = cacheService.findUser(1l,"wang","yunfei");
        //cacheService.findAddress(1l,"anhui","hefei");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/get",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public ResponseEntity<?> getCache() {
        User user = cacheService.findUser(1l,"wang","yunfei");
        //Address address =cacheService.findAddress(1l,"anhui","hefei");
        System.out.println("我这里没执行查询");
        System.out.println("user:"+"/"+user.getFirstName()+"/"+user.getLastName());
        //System.out.println("address:"+"/"+address.getProvince()+"/"+address.getCity());
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/delete",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public ResponseEntity<?> deleteCache() {
        cacheService.removeUser(1l);
        //Address address =cacheService.findAddress(1l,"anhui","hefei");
        //System.out.println("address:"+"/"+address.getProvince()+"/"+address.getCity());
        return ResponseEntity.ok("remove user success");
    }

    @RequestMapping(value = "/save",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public ResponseEntity<?> saveCache() {
        User user = new User(2l,"norman","tian");
        User u = cacheService.saveUser(user);
        //Address address =cacheService.findAddress(1l,"anhui","hefei");
        //System.out.println("address:"+"/"+address.getProvince()+"/"+address.getCity());
        return ResponseEntity.ok(u);
    }




}
