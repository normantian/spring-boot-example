package org.sun.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.sun.spring.config.MyConfig;
import org.sun.spring.entity.ThreadPoolBean;

import java.util.List;


/**
 * Created by tianfei on 17/2/27.
 */
@RequestMapping("/test")
@RestController
public class ConfigController {
    @Autowired
    private MyConfig myConfig;

    @Autowired
    private ThreadPoolBean threadPoolBean;

    @RequestMapping(value = "/config",
            method = RequestMethod.GET,
//            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
//            MediaType.APPLICATION_XML_VALUE}
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
            )
    public ResponseEntity<?> getConfig() {
        return ResponseEntity.ok(myConfig.getServers());
    }


    @RequestMapping(value = "/bean",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public ResponseEntity<?> getThreadBean() {
        return ResponseEntity.ok(this.threadPoolBean);
    }


}
