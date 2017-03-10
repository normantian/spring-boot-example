package org.sun.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.sun.spring.entity.User;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by tianfei on 17/3/10.
 */
@Repository
public class UserDao {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valOps;

    public void stringRedisTemplateDemo(String key, String value) {
        valOpsStr.set(key, value);
    }

    public void save(User user) {
        long timeout = 60;
        valOps.set("User:" + user.getId().toString(), user, timeout, TimeUnit.SECONDS);
    }

    public String getString(String key) {
        return valOpsStr.get(key);
    }

    public User getUser() {
        return (User) valOps.get("User:1");
    }


}
