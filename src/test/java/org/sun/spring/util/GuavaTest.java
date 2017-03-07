package org.sun.spring.util;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by tianfei on 17/2/27.
 */
public class GuavaTest {


    @Test
    public void splitter() throws Exception{
        Iterable<String> iterator = Splitter.on(" ").split("1 2 3");
        iterator.forEach(s -> {
            System.out.println(s);
        });
    }

    @Test
    public void mapSplitter() throws Exception{
        Map<String,String> map = Splitter.on("#").withKeyValueSeparator(":").split("1:2#3:4");
        System.out.println(map);

    }
}
