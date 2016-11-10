package org.sun.spring.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sun.spring.entity.Seckill;
import org.sun.spring.util.HttpClientUtil;
import org.sun.spring.util.JsonUtil;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.sun.spring.util.HttpClientUtil.doGet;

@RestController
@RequestMapping(produces = APPLICATION_JSON_UTF8_VALUE) //返回的MIME数据类型
public class SiteController {

    @RequestMapping(value = "/child/{id}",
            method = RequestMethod.DELETE)
    public String deleteChild(@PathVariable("id") Integer id){
        return "delete id is " + id;
    }

    @RequestMapping(value = "/child",
            method = RequestMethod.POST,
            consumes = APPLICATION_JSON_UTF8_VALUE//要求请求头必须有MIME类型
            )
    public ResponseEntity<?> post(@RequestBody Seckill seckill){
        System.out.println(seckill);
        seckill.setSeckillId(1001);
        return new ResponseEntity<>(seckill,HttpStatus.OK);
    }

    @RequestMapping(value = "/ip",
            method = RequestMethod.GET
    )
    public String getIp(HttpServletRequest request) throws UnsupportedEncodingException {
        String json = HttpClientUtil.doGet("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=");
        System.out.println(json);
        //String returnJson = new String(json);
        StringBuilder sb = new StringBuilder("your ip is ");
        sb.append(request.getRemoteAddr()).append("\n");
        sb.append(JsonUtil.findValuesByKey(json,"country").get(0) + "\n");
        sb.append(JsonUtil.findValuesByKey(json,"city").get(0)+"\n");
        sb.append(JsonUtil.findValuesByKey(json,"district").get(0)+"\n");
        return sb.toString();
        //return sb.append(JsonUtil.findValuesByKey(json,"country").get(0)).toString();
        //return request.getRemoteHost() + " " + request.getRemoteAddr();

    }


    @RequestMapping(value = "/code/{codeNo}",
            method = RequestMethod.GET,
            consumes = APPLICATION_JSON_UTF8_VALUE, //要求请求头必须有MIME类型
            produces = APPLICATION_JSON_UTF8_VALUE //返回的MIME数据类型
    )
    //@RolesAllowed({"users","administrators"})
    public Map<String, Object> getMap(@PathVariable("codeNo") Integer codeNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", codeNo);
        return result;
    }

    @RequestMapping(value = "/list",
            method = RequestMethod.GET,
            //consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, //要求请求头必须有MIME类型
            produces = APPLICATION_JSON_UTF8_VALUE //返回的MIME数据类型
    )
    //@RolesAllowed({"users","administrators"})
    public List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("hello2");
        return list;
    }


    @RequestMapping(value = "/listSeckill",
            method = RequestMethod.GET,
//            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,
//                    MediaType.APPLICATION_XML_VALUE}, //要求请求头必须有MIME类型
            produces = {APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE} //返回的MIME数据类型
    )
    //@RolesAllowed({"users","administrators"})
    public List<Seckill> listSeckill() {
        List<Seckill> list = new ArrayList<>();
        Seckill seckill = new Seckill();
        seckill.setSeckillId(1000L);
        seckill.setName("test");
        seckill.setNumber(10);
        seckill.setCreateTime(new DateTime(2016,11,20,0,0,0).toDate());
        seckill.setEndTime(new DateTime(2017,1,20,0,0,0).toDate());
        seckill.setStartTime(new DateTime(2016,11,21,0,0,0).toDate());

        Seckill seckill2 = new Seckill();
        seckill2.setSeckillId(1001L);
        seckill2.setName("test2");
        seckill2.setNumber(23);
        seckill2.setCreateTime(new DateTime(2016,11,20,12,0,0).toDate());
        seckill2.setEndTime(new DateTime(2017,1,20,3,0,0).toDate());
        seckill2.setStartTime(new DateTime(2016,11,21,4,0,0).toDate());
        list.add(seckill);
        list.add(seckill2);
        return list;
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

    @RequestMapping(value = "/hello/{userName}",
            method = RequestMethod.GET)
    @PermitAll
    public String hello(@PathVariable("userName") String userName) {
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
