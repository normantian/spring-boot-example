package org.sun.spring.service.impl;

import org.springframework.stereotype.Service;
import org.sun.spring.api.GzClient;
import org.sun.spring.service.TestService;

/**
 * Created by tianfei on 16/11/15.
 */
@Service("testService")
public class TestServiceImpl extends AbstractService implements TestService {

    @Override
    public String create(String name) {
        super.createToken();
        return gzClient.getAccessToken();
    }

    @Override
    public String refresh() {
        super.refreshToken();
        return gzClient.getAccessToken();
    }

    @Override
    public GzClient getGzClient() {
        return super.gzClient;
    }
}
