package org.sun.spring.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Norman on 2016/7/21.
 * 基于jackson的JSON工具类
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = null;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        // 对于空的对象转json的时候不抛出错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 禁用遇到未知属性抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
        // 允许单值作为数组
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        //序列化枚举类型以toString作为输出，默认false，即默认以name()输出
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, false);
        //null值的property不会显示
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static class TestBean{
        private String name;
        private Integer id;
        public TestBean(){

        }
        public TestBean(Integer id,String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "TestBean{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

    /***
     * jsonString中是否包含某个属性
     * @param jsonString
     * @param key
     * @return boolean
     */
    public static boolean hasProperty(String jsonString,String key){
        boolean hasPro = false;
        try {
            JsonNode node = objectMapper.readTree(jsonString);
            hasPro = node.has(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hasPro;
    }

    /****
     *
     * @param jsonString
     * @param key
     * @return <p>List<String></p>
     */
    public static List<String> findValuesByKey(String jsonString,String key){
        List<String> retList = null;

        try {
            JsonNode node = objectMapper.readTree(jsonString);
            retList = node.findValuesAsText(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retList;
    }

    /***
     * JsonUtil constructor 不能被反射
     */
    private JsonUtil(){
        // Superess default constructor for noninstantiability
        throw new AssertionError();
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... classes){
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, classes);
    }

    /***
     * bean 转 JSON
     * @param obj
     * @return
     */
    public static String beanToJson(Object obj) {
        String json = "";
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * json转Map
     * @param jsonString
     * @return
     */
    public static Map<?, ?> jsonToMap(String jsonString){
        JsonNode jn = null;
        try {
            jn = objectMapper.readTree(jsonString);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return (Map<?, ?>)JsonNodeToMap(jn);
    }

    public static Object JsonNodeToMap(JsonNode root) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if (root == null) {
            return map;
        }
        if (root.isArray()) {
            List<Object> list = new ArrayList<Object>();
            for (JsonNode node : root) {
                Object nmp = JsonNodeToMap(node);
                list.add(nmp);
            }
            return list;
        }
        if (root.isTextual()) {
            try {
                return ((TextNode) root).asText();
            } catch (Exception e) {
                return root.toString();
            }
        }
        Iterator<?> iter = root.fields();
        while (iter.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry entry = (Map.Entry)iter.next();
            String key = (String)entry.getKey();
            JsonNode ele = (JsonNode)entry.getValue();
            if (ele.isObject())
                map.put(key, JsonNodeToMap(ele));
            else if (ele.isTextual())
                map.put(key, ((TextNode)ele).asText());
            else if (ele.isArray())
                map.put(key, JsonNodeToMap(ele));
            else {
                map.put(key, ele.toString());
            }
        }
        return map;
    }

    public static <T> T jsonToBean(String jsonString, Class<T> clazz){
        return jsonToBeans(jsonString,clazz).get(0);
    }

    /***
     * JSON 转 Bean,集合自带泛型
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return List<T>
     */
    public static <T> List<T> jsonToBeans(String jsonString, Class<T> clazz) {
        List<T> lt = null;

        try {
            JsonNode jNode = objectMapper.readTree(jsonString);
            //json is an array
            if(jNode.isArray()){
                JavaType javaType = getCollectionType(ArrayList.class,clazz);
                    lt = objectMapper.readValue(jsonString, javaType);

            } else{
                //如果是个单值的json,则返回一个只有一个元素的List(singletonList)
                lt = Collections.singletonList((T)objectMapper.readValue(jsonString, clazz));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lt;
    }

}
