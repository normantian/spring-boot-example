package org.sun.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@RestController
public class SiteController {

    @RequestMapping(value = "/code/{codeNo}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, //要求请求头必须有MIME类型
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE //返回的MIME数据类型
    )
    @RolesAllowed({"users","administrators"})
    public Map<String, Object> actionIndex(@PathVariable("codeNo") Integer codeNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", codeNo);
        return result;
    }

    //region Description
    /*@RequestMapping(value = "/code/{codeNo}")
    public Map<String, Object> code(@PathVariable("codeNo") Integer codeNo) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("code", codeNo);
        return result;
    }*/
    //endregion

    //<editor-fold desc="Description">
    @RequestMapping(value = "/hello",
            method = RequestMethod.GET)
    @PermitAll
    public String testString(@RequestParam(name = "userName", required = false) String userName) {
        return userName == null ? "Hello man" : sayHello(userName);
    }

    @RequestMapping(value = "/div/{a}/{b}",
            method = RequestMethod.GET)
    //@PermitAll
    public Integer testString(@PathVariable("a") Integer a,@PathVariable("b") Integer b) {
        return a/b;
    }



    private String sayHello(final String userName) {
        return "Hello "+userName;
    }
    //</editor-fold>



}
