package org.sun.spring.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tianfei on 16/10/21.
 */
public class GsonTest {

    public static void main(String[] args) {
        Gson gson = new Gson();
        String jsonArray = "[\"Java\",\"Scala\"]";
        String[] strings = gson.fromJson(jsonArray,String[].class);
        System.out.println(Arrays.toString(strings));

        List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>(){}.getType());
        System.out.println(stringList);

        User user = new User("norman",32);
        System.out.println(gson.toJson(user));
        System.out.println(createGson().toJson(user));


        Category category = new Category(1,"ca1",new ArrayList<Category>(),new Category(2,"parent"));
        System.out.println(createGsonExpose().toJson(category));

    }

    public static Gson createGson(){
        return new GsonBuilder().serializeNulls() //序列化null,默认不输出 null的值
                .setDateFormat("yyyy-MM-dd")
                .disableInnerClassSerialization() // 禁止序列化内部类
                .disableHtmlEscaping() //禁止转义html标签
                .setPrettyPrinting() //格式化输出
                .create();
    }

    public static Gson createGsonExpose() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .create();
    }

}
