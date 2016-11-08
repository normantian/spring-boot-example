package org.sun.spring.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.sun.spring.Bootstrap;
import org.sun.spring.SpringBootBaseTest;
import org.sun.spring.SpringBootMvcBaseTest;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tianfei on 16/11/7.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes={Bootstrap.class},
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
public class SiteControllerTest extends SpringBootMvcBaseTest {
//    @Autowired
//    private MockMvc mvc;

//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Before
//    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }

    @Test
    public void actionIndex() throws Exception {
        String responseString =
                super.mvc.perform(get("/code/123").accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) ////返回的状态是200
                .andDo(print())
                .andReturn().getResponse().getContentAsString(); ////打印出请求和相应的内容
        System.out.println(responseString);

    }

    @Test
    public void getMap() throws Exception {
        String url = base.toString() + "code/{codeNo}";

        Map<String,Object> param = new HashMap<>();
        param.put("codeNo",100);

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept",MediaType.APPLICATION_JSON_UTF8_VALUE);
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        //HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        HttpEntity<String> requestEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Map> body = restTemplate.exchange(url, HttpMethod.GET,requestEntity,Map.class,param);
        Map<String, Object> resultMap = body.getBody();
        resultMap.forEach((k,v) -> System.out.println("key=" + k + " value=" + v));

//        ResponseEntity<String> respsonse = restTemplate.getForEntity(url,String.class,1001);
//        Map<String,Object> map = restTemplate.getForObject(url,Map.class,1001);
//        map.forEach((k,v) -> System.out.println(k + " " + v.toString()));
    }



    @Test
    public void testString() throws Exception {
        String responseString = this.mvc.perform(get("/hello").accept(MediaType.APPLICATION_JSON_UTF8).param("userName","norman"))
                .andExpect(status().isOk()) ////返回的状态是200
                .andDo(print())
                .andReturn().getResponse().getContentAsString(); ////打印出请求和相应的内容
        System.out.println("respsoneString : " + responseString);
    }

    @Test
    public void testHello() throws Exception {
        Map<String,Object> param = new HashMap<>();
        param.put("userName","norman");
        String url2 = base.toString() + "hello?userName={userName}";
        String returnString = restTemplate.getForObject(url2,String.class,param);
        System.out.println(returnString);
    }

    @Test
    public void testRestfulHello() throws Exception {
        String url = base.toString() + "hello/{userName}";
        ResponseEntity<String> respsonse = restTemplate.getForEntity(url,String.class,"normanTian");
        System.out.println(respsonse.getBody());

    }

}