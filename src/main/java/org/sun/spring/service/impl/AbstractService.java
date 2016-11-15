package org.sun.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.sun.spring.api.GzClient;

/**
 * Created by tianfei on 16/11/15.
 */
public class AbstractService {
    @Autowired
    @Qualifier("gzClient")
    protected GzClient gzClient;

    protected final void refreshToken(){
        gzClient.refreshAccessToken();
    }

    protected final void createToken(){
        gzClient.createAccessToken();
    }
}
