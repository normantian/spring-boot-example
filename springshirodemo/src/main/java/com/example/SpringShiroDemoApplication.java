package com.example;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Configuration
@ControllerAdvice
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringShiroDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringShiroDemoApplication.class, args);
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Map> handleException(AuthorizationException e) {

        // you could return a 404 here instead (this is how github handles 403, so the user does NOT know there is a
        // resource at that location)
        //log.debug("AuthorizationException was thrown", e);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", HttpStatus.FORBIDDEN.value());
        map.put("message", e.getLocalizedMessage());
//        model.addAttribute("errors", map);

        return new ResponseEntity<Map>(map, HttpStatus.FORBIDDEN);
    }
}
