package org.sun.spring.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.sun.spring.entity.Address;
import org.sun.spring.entity.User;
import org.sun.spring.service.CacheService;

/**
 * Created by tianfei on 17/3/9.
 */
@Service("cacheService")
public class CacheServiceImpl implements CacheService {
    @Override
//    @Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")
    @Cacheable(value = "userCache", key = "T(String).valueOf('User').concat(':').concat(#id)")
//    @Cacheable(value = "userCache", key = "#id")
    public User findUser(Long id, String firstName, String lastName) {
        System.out.println("userCache无缓存的时候调用这里");
        return new User(id,firstName,lastName);
    }

    @Override
//    @CacheEvict(value="userCache", key = "#id.toString()")
    @CacheEvict(value="userCache",key = "T(String).valueOf('User').concat(':').concat(#id)")
//    @CacheEvict(value="userCache")
    public void removeUser(Long id) {
        System.out.println("删除了id,key为" + id +"的数据缓存");
    }

    @Override
//    @CachePut(value = "userCache", key = "#p0.toString()")
//    @CachePut(value="userCache", key = "T(String).valueOf('User').concat(':').concat(#user.id)")
    @CachePut(value="userCache", key = "'User:'+ #user.id")
//    @CachePut(value="userCache",key = "#id")
    public User saveUser(User user) {
        System.out.println("userCache无缓存的时候调用这里");
        return user;
    }


    @Override
    @Cacheable(value = "addresscache", key = "#p0.toString()")
//    @Cacheable(value = "addresscache",keyGenerator = "wiselyKeyGenerator")
    public Address findAddress(Long id, String province, String city) {
        System.out.println("addresscache无缓存的时候调用这里");
        return new Address(id,province,city);
    }
}
