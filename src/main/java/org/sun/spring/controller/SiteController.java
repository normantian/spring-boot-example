package org.sun.spring.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteController {

    @RequestMapping("/code")
    public HashMap<String, Object> actionIndex() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("code", 1);
        return result;
    }

}
