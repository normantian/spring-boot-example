package org.sun.spring.util;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by tianfei on 16/11/11.
 */
public class JsonPathUtilTest {

    File jsonFile;
    InputStream inputStream;
    URL url;

    @Before
    public void setUp() throws Exception {
        //jsonFile = new File(getClass().getResource("demo.json").getFile());
        //url = getClass().getResource("/demo.json");

        inputStream = this.getClass().getResourceAsStream(File.separator + "demo.json");
    }

    @Test
    public void getNow() throws Exception{
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void getAllAuthors() throws IOException {
        //List<String> authors = JsonPath.parse(inputStream).read("$.store.book[*].author");
        //List<Map<String, Object>>  authors = JsonPath.parse(inputStream).read("$..book[?(@.isbn)]");
        //List<String> user = JsonPath.parse(inputStream).read("$.user");
        //System.out.println(user);
        ReadContext rctx = JsonPath.parse(inputStream);


        List<Map<String, Object>> book = rctx.read("$.store.book[*]");
        System.out.println(book);


        Integer money =rctx.read("$.expensive");
        System.out.println(money);

        //System.out.println(rctx.read());



        //List<String> authors = JsonPath.parse(inputStream).read("$.test");

        //authors.forEach(author -> System.out.println(author));
    }
}