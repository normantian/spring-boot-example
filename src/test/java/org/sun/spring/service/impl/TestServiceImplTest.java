package org.sun.spring.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sun.spring.SpringBootBaseTest;
import org.sun.spring.service.TestService;

import static org.junit.Assert.*;

/**
 * Created by tianfei on 16/11/15.
 */
public class TestServiceImplTest extends SpringBootBaseTest {
    @Autowired
    TestService testService;
    @Test
    public void hello() throws Exception {
        testService.create("Norman");

        System.out.println(testService.getGzClient().getAccessToken());

        testService.refresh();

        System.out.println(testService.getGzClient().getAccessToken());
    }

}