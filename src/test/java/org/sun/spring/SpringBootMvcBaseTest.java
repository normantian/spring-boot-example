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

/**
 * Created by tianfei on 16/11/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={Bootstrap.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Ignore
public class SpringBootMvcBaseTest extends SpringBootBaseTest {
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
}

