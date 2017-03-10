package org.sun.spring.service;

import org.sun.spring.entity.Address;
import org.sun.spring.entity.User;

/**
 * Created by tianfei on 17/3/9.
 */
public interface CacheService {
    User findUser(Long id, String firstName,String lastName);

    void removeUser(Long id);

    User saveUser(User user);

    Address findAddress(Long id, String province, String city);
}
