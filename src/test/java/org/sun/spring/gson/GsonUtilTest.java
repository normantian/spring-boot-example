package org.sun.spring.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sun.spring.Bootstrap;
import org.sun.spring.SpringBootBaseTest;

import static org.sun.spring.gson.GsonUtil.createGson;
import static org.sun.spring.gson.GsonUtil.createGsonExpose;

/**
 * Created by tianfei on 16/11/7.
 */
public class GsonUtilTest extends SpringBootBaseTest{
    @Test
    public void gsonTest() throws Exception {

        Gson gson = new Gson();
        String jsonArray = "[\"Java\",\"Scala\"]";
        String[] strings = gson.fromJson(jsonArray,String[].class);
        System.out.println(Arrays.toString(strings));

        List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>(){}.getType());
        System.out.println(stringList);

        User user = new User("norman",32);
        User user2 = new User("lisa",30);
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user2);
        String json = gson.toJson(list);
        System.out.println(json);
        List<User> l2 = gson.fromJson(json,new TypeToken<List<User>>(){}.getType());
        l2.forEach(user1 -> System.out.println(user1));
        System.out.println(gson.toJson(user));
        System.out.println(createGson().toJson(user));


        List<Category> children = new ArrayList<Category>();
        children.add(new Category(3,"child"));

        Category category = new Category(1,"ca1", children
                ,
                new Category(2,"parent"));
        System.out.println(createGsonExpose().toJson(category));

    }

}