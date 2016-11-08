package org.sun.spring;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URL;

import static org.apache.coyote.http11.Constants.a;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by tianfei on 16/11/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={Bootstrap.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Ignore
public class SpringBootMvcBaseTest extends SpringBootBaseTest {
    // 根据启动时port注入
    @LocalServerPort
    private int port; // application port

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected MockMvc mvc;

    protected URL base;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.base = new URL("http://localhost:" + port + "/");
    }

    /***
     * 实际运行结果是否等于期望值
     * @param actual
     * @param expected
     * @param <T>
     */
    public static <T> void assertEqual(T actual, T expected){
        assertThat(actual,equalTo(expected));
    }
}

