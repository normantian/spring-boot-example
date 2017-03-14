package org.sun.spring.dao.redis;

import org.springframework.stereotype.Service;
import org.sun.spring.entity.User;

/**
 * Created by tianfei on 17/3/10.
 */
@Service("iRedisService")
public class RedisServiceImpl extends IRedisService<User> {
    private static final String REDIS_KEY = "TEST_REDIS_KEY";
    /**
     * 存入redis中的key
     *
     * @return
     */
    @Override
    protected String getRedisKey() {
        return this.REDIS_KEY;
    }
}
