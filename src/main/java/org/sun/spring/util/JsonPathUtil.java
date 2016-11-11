package org.sun.spring.util;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.jayway.jsonpath.JsonPath.parse;
import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;

/**
 * Created by tianfei on 16/11/11.
 */
public class JsonPathUtil {
    public static void main(String[] args) {
        String json = "{\"date_as_long\" : 1411455611975}";

        String json2 = "{\n" +
                "    \"store\": {\n" +
                "        \"book\": [\n" +
                "            {\n" +
                "                \"category\": \"reference\",\n" +
                "                \"author\": \"Nigel Rees\",\n" +
                "                \"title\": \"Sayings of the Century\",\n" +
                "                \"price\": 8.95\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Evelyn Waugh\",\n" +
                "                \"title\": \"Sword of Honour\",\n" +
                "                \"price\": 12.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Herman Melville\",\n" +
                "                \"title\": \"Moby Dick\",\n" +
                "                \"isbn\": \"0-553-21311-3\",\n" +
                "                \"price\": 8.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"J. R. R. Tolkien\",\n" +
                "                \"title\": \"The Lord of the Rings\",\n" +
                "                \"isbn\": \"0-395-19395-8\",\n" +
                "                \"price\": 22.99\n" +
                "            }\n" +
                "        ],\n" +
                "        \"bicycle\": {\n" +
                "            \"color\": \"red\",\n" +
                "            \"price\": 19.95\n" +
                "        }\n" +
                "    },\n" +
                "    \"expensive\": 10\n" +
                "}";

        //Date date = JsonPath.parse(json).read("$['date_as_long']", Date.class);
        //System.out.println(date.toString());


        List<String> authors = JsonPath.read(json2,"$.store.book[*].author");

        authors.forEach((s -> System.out.println(s)));

        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

        List<Map<String, Object>> expensiveBooks = JsonPath
                //.using(configuration)
                .parse(json2)
                .read("$..book[1:]",List.class);
                //.read("$.store.book[?(@.price > 10)]", List.class);

        expensiveBooks.forEach(book -> System.out.println(book));


        Filter cheapFictionFilter = Filter.filter(
                where("category").is("fiction").and("price").lte(10D)
        );

//        List<Map<String, Object>> books =
//                parse(json2).read("$.store.book[?]", cheapFictionFilter);


        List<Map<String, Object>> books =
                parse(json2).read("$.store.book[?(@.price < 10 && @.category == 'fiction')]");
        System.out.println("----------cheapFictionFilter------------");
        books.forEach(book -> System.out.println(book));
    }
}
