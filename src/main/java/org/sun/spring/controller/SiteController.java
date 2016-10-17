package org.sun.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class SiteController {

    @RequestMapping(value = "/code/{codeNo}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, //要求请求头必须有MIME类型
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE //返回的MIME数据类型
    )
    public HashMap<String, Object> actionIndex(@PathVariable("codeNo") Integer codeNo) {
        HashMap<String, Object> result = new HashMap<String, Object>();
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
    public String testString(@RequestParam(name = "userName", required = false) String userName) {
        return userName == null ? "Hello man" : "Hello " + userName;
    }
    //</editor-fold>

}
