package org.sun.spring.service;

import org.sun.spring.api.GzClient;

/**
 * Created by tianfei on 16/11/15.
 */
public interface TestService {

    String create(String name);

    String refresh();

    GzClient getGzClient();
}
