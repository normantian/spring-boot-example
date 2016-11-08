package org.sun.spring;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by tianfei on 16/11/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={Bootstrap.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Ignore
public class SpringBootBaseTest {
    @Before
    public void setUp() throws Exception {
        System.out.println("test init ...");
    }
    @After
    public void tearDown() throws Exception {
        System.out.println("test finish .");
    }
}

