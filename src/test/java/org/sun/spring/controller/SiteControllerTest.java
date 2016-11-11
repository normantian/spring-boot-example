package org.sun.spring.controller;

import org.joda.time.DateTime;
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
import org.sun.spring.entity.Seckill;
import org.sun.spring.util.HttpClientUtil;
import org.sun.spring.util.JsonUtil;

import static org.apache.coyote.http11.Constants.a;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.sun.spring.util.HttpClientUtil.doGet;
import static org.sun.spring.util.HttpClientUtil.doPost;

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

    /**
     * Mock mvc test demo
     * @throws Exception
     */
    @Test
    public void actionIndex() throws Exception {
        Map<String,Integer> map = new HashMap<>();
        map.put("code",123);
        System.out.println(JsonUtil.beanToJson(map));
        String responseString =
                super.mvc.perform(get("/code/123").accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk()) //返回的状态是200
                        .andExpect(content().string("{\"code\":123}"))
                        .andExpect(jsonPath("$.code").value(123))
                        .andExpect(content().json(JsonUtil.beanToJson(map)))
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString(); //打印出请求和相应的内容
        System.out.println(responseString);

    }


    @Test
    public void getStringList() throws Exception {
        String url = base.toString() + "list";
        List<String> list = restTemplate.getForObject(url, List.class);
        list.forEach(l -> System.out.println(l));
    }

    @Test
    public void getStringList2() throws Exception {
        String url = base.toString() + "list";
        String json = HttpClientUtil.doGet(url);
        System.out.println(JsonUtil.jsonToBeans(json,String.class));
    }

    @Test
    public void getSeckillList() throws Exception {
        String url = base.toString() + "listSeckill";
        Seckill[] seckills = restTemplate.getForObject(url,Seckill[].class);
//        ResponseEntity<? extends ArrayList<Seckill>> responseEntity =
//                restTemplate.getForEntity(url,(Class<? extends ArrayList<Seckill>>)List.class);
//        List<Seckill> list = responseEntity.getBody();
//        list.forEach(seckill -> System.out.println(seckill));
        //System.out.println(responseEntity.getBody());

        //seckills.forEach(seckill -> System.out.println(seckill));

        //返回对象是集合范型List<Object> 时,先转成对象数组,然后再进行数组转List
        System.out.println("print Seckill[] :");
        for (int i = 0; i < seckills.length; i++) {
            System.out.println(seckills[i]);
        }

        System.out.println("print List<Seckill> : ");
        List<Seckill> list = Arrays.asList(seckills);
        list.forEach(seckill -> System.out.println(JsonUtil.beanToJson(seckill)));
    }
    @Test
    public void getSeckillList2() throws Exception {
        String url = base.toString() + "listSeckill";
        String json = HttpClientUtil.doGet(url);
        List<Seckill> seckills = JsonUtil.jsonToBeans(json,Seckill.class);
        seckills.forEach(seckill -> System.out.println(JsonUtil.beanToJson(seckill)));
    }

    @Test
    public void getMap() throws Exception {
        String url = base.toString() + "code/{codeNo}";

        Map<String,Object> param = new HashMap<>();
        param.put("codeNo",100);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept",MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        //HttpEntity<String> requestEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Map> body = restTemplate
                .exchange(url, HttpMethod.GET,requestEntity,Map.class,param);
        Map<String, Object> resultMap = body.getBody();
        resultMap.forEach((k,v) -> System.out.println("key=" + k + " value=" + v));

//        ResponseEntity<String> respsonse = restTemplate.getForEntity(url,String.class,1001);
//        Map<String,Object> map = restTemplate.getForObject(url,Map.class,1001);
//        map.forEach((k,v) -> System.out.println(k + " " + v.toString()));
    }


    @Test
    public void getMap2() throws Exception {
        String url = base.toString() + "code/{codeNo}";

        Map<String,Object> param = new HashMap<>();
        param.put("codeNo",100);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept",MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        //HttpEntity<String> requestEntity = new HttpEntity<>(new HttpHeaders());
        String json = HttpClientUtil.doGet(url,1000);
        System.out.println(json);
//        ResponseEntity<Map> body = restTemplate
//                .exchange(url, HttpMethod.GET,requestEntity,Map.class,param);
//        Map<String, Object> resultMap = body.getBody();
//        resultMap.forEach((k,v) -> System.out.println("key=" + k + " value=" + v));

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
    public void testHelloWithoutParam() throws Exception {
        String url2 = base.toString() + "hello";
        String returnString = restTemplate.getForObject(url2,String.class);
        //assertThat(returnString,equalTo("Hello man"));
        SpringBootMvcBaseTest.assertEqual(returnString, "Hello man");
        System.out.println(returnString);
    }

    @Test
    public void testHelloWithParam() throws Exception {
        Map<String,Object> param = new HashMap<>();
        param.put("userName","norman");
        String url2 = base.toString() + "hello?userName={userName}";
        String returnString = restTemplate.getForObject(url2,String.class,param);
        //assertThat(returnString,equalTo("Hello man"));
        SpringBootMvcBaseTest.assertEqual(returnString, "Hello norman");
        System.out.println(returnString);
    }

    @Test
    public void testRestfulHello() throws Exception {
        String url = base.toString() + "hello/{userName}";
        ResponseEntity<String> respsonse = restTemplate.getForEntity(url,String.class,"normanTian");
        //assertThat(respsonse.getBody(),equalTo("Hello normanTian"));
        SpringBootMvcBaseTest.assertEqual(respsonse.getBody(),"Hello normanTian");
        System.out.println(respsonse.getBody());
        System.out.println(respsonse.getStatusCode());
        System.out.println(respsonse.getStatusCodeValue());
    }

    @Test
    public void testDeleteMethod() throws Exception {
        String url = base.toString() + "child/{id}";
        HttpClientUtil.doDelete(url,100);
    }

    @Test
    public void testPostMethod2() throws Exception {
        String url = base.toString() + "child" ;
        Seckill seckill = new Seckill();
        seckill.setName("test");
        seckill.setNumber(10);
        seckill.setCreateTime(new DateTime(2016,11,20,0,0,0).toDate());
        seckill.setEndTime(new DateTime(2017,1,20,0,0,0).toDate());
        seckill.setStartTime(new DateTime(2016,11,21,0,0,0).toDate());
        String postJson = HttpClientUtil.doPost(url,seckill);
        System.out.println(JsonUtil.beanToJson(JsonUtil.jsonToBean(postJson,Seckill.class)));
    }

    @Test
    public void testPostMethod() throws Exception {
        String url = base.toString() + "child" ;
        Seckill seckill = new Seckill();
        seckill.setSeckillId(1000L);
        seckill.setName("test");
        seckill.setNumber(10);
        seckill.setCreateTime(new DateTime(2016,11,20,0,0,0).toDate());
        seckill.setEndTime(new DateTime(2017,1,20,0,0,0).toDate());
        seckill.setStartTime(new DateTime(2016,11,21,0,0,0).toDate());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept",MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        String json = JsonUtil.beanToJson(seckill);
        HttpEntity<String> requestEntity = new HttpEntity<>(json,headers);
        //HttpEntity<String> requestEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Seckill> responseEntity = restTemplate
                .exchange(url, HttpMethod.POST,requestEntity,Seckill.class);

        System.out.println(responseEntity.getBody());

//        restTemplate.postForEntity(url,String.class,)
//        String postJson = HttpClientUtil.doPost(url,seckill);
//        System.out.println(JsonUtil.jsonToBean(postJson,Seckill.class));
    }

}