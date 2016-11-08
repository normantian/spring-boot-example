package org.sun.spring.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by tianfei on 16/10/21.
 */
public class GsonUtil {

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
