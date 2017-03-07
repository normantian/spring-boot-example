package org.sun.spring.util;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import org.junit.Test;

import java.util.Collection;
import java.util.SortedSet;

/**
 * 不可变对象有很多优点，包括：

 当对象被不可信的库调用时，不可变形式是安全的；
 不可变对象被多个线程调用时，不存在竞态条件问题
 不可变集合不需要考虑变化，因此可以节省时间和空间。所有不可变的集合都比它们的可变形式有更好的内存利用率（分析和测试细节）；
 不可变对象因为有固定不变，可以作为常量来安全使用。
 * Created by tianfei on 17/2/27.
 */
public class ImmutableTest {

    @Test
    public void test() throws Exception{
        ImmutableSet<String> foobar = ImmutableSortedSet.of("foo", "bar", "baz","foo");
        thingamajig(foobar);

        System.out.println(foobar);
        System.out.println(foobar.asList().get(2));


    }
    void thingamajig(Collection<String> collection) {
        ImmutableList<String> defensiveCopy = ImmutableList.copyOf(collection);
    }

}
