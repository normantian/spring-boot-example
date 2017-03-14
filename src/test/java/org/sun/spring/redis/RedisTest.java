package org.sun.spring.redis;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.sun.spring.SpringBootBaseTest;
import org.sun.spring.dao.redis.IRedisService;
import org.sun.spring.entity.User;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by tianfei on 17/3/10.
 */
public class RedisTest extends SpringBootBaseTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IRedisService iRedisService;

    @Test
    public void testRedis() throws Exception{
        User user2 = new User(2L,"TIAN","FEI");
        iRedisService.put("user2",user2,-1);

        User user1 = new User(1L,"Norman","FEI");
        iRedisService.put("user1",user1,-1);

        User user3 = new User(3L,"List","Li");
        iRedisService.put("user3",user3,-1);

        System.out.println("add success end...");

        System.out.println("get all keys...");
        Set<String> keys = iRedisService.getKeys();
        keys.forEach(key-> System.out.println(key));
        System.out.println("get all keys end...");

        System.out.println("get all users...");
        List<User> users = iRedisService.getAll();
        for(User u : users){
            System.out.println(u);
        }
        System.out.println("get all users end...");

        System.out.println("remove user 1...");

        iRedisService.remove("user1");

        System.out.println("user 1 is exist :" + iRedisService.isKeyExists("user1"));
        System.out.println("user 2 is exist :" + iRedisService.isKeyExists("user2"));

        System.out.println("size : " + iRedisService.count());
        //iRedisService.empty();




    }



    private void testReplaceValue(BoundValueOperations<String,String> stringTemplate, long offset){
//从value下标,第0位开始替换原有字符串
        stringTemplate.set("test1",offset);
        String value1 = stringTemplate.get();
        System.out.println(stringTemplate.getKey()+"的值为:"+value1);
    }

    private boolean hasKey(String key){
        return stringRedisTemplate.hasKey(key);
    }

    @Test
    public void testSetValue() throws Exception{
        //save string
        stringRedisTemplate.opsForValue().set("aaa","111");
        Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaa"));

        final String key = "hexiaowu";
        BoundValueOperations<String,String> stringTemplate = stringRedisTemplate.boundValueOps(key);


        //赋值key
        stringTemplate.set("test");
        //获取value
        String value = stringTemplate.get();
        System.out.println(key+"的值为:"+value);

        testReplaceValue(stringTemplate,0);
        testReplaceValue(stringTemplate,1);
        testReplaceValue(stringTemplate,7);

        System.out.println("has key "+key+" : " +hasKey(key));

        stringTemplate.set("testTimeout",60,TimeUnit.SECONDS);
        Long expire = stringTemplate.getExpire();
        System.out.println(key+"的缓存时间为:"+expire);

//        //从value下标,第1位开始替换原有字符
//        stringTemplate.set("test2",1);
//        String value2 = (String)stringTemplate.get();
//        System.out.println(key+"的值为:"+value2);
////从value下标第7位进行替换,如果超过原有字符串长度,差额中间补齐并且则将原有字符串跟新的进行拼接,
//        stringTemplate.set("test3",7);
//        String value3 = (String)stringTemplate.get();
//        System.out.println(key+"的值为:"+value3);
///**
// * 设置value缓存时间 V value, long timeout, TimeUnit unit
// * 三个字段分别对应 value,缓存时间,缓存单位,例如天,小时等,具体的,看TimeUnit源码,上面有描写,这里就不一一介绍了
// */
////设置超时时间为1天
//        stringTemplate.set("testTimeout",1, TimeUnit.DAYS);
//        //获取缓存时间,单位 秒
//        Long expire = stringTemplate.getExpire();
//        System.out.println(key+"的缓存时间为:"+expire);

    }

    @After
    public void tearDown() throws Exception {
        stringRedisTemplate.delete("aaa");
        super.tearDown();

    }
}
