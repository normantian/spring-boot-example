package org.sun.spring.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tianfei on 16/11/8.
 * JsonComponent
 * 日期json格式转化 yyyy-MM-dd HH:mm:ss
 * Serializer Deserializer
 */
@JsonComponent
public class JsonConfig {
    public static class Serializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date value,
                              JsonGenerator jsonGenerator,
                              SerializerProvider provider)
                throws IOException, JsonProcessingException {
            System.out.println("======================");
            System.out.println("Date Serializer " + value.toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            jsonGenerator.writeString(sdf.format(value));
        }
    }

    public static class Deserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException{

            System.out.println("======================");
            System.out.println("Date Deserializer " + jp.getValueAsString());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date date = new Date();
            try {
                date = sdf.parse(jp.getValueAsString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;

        }

    }
}
