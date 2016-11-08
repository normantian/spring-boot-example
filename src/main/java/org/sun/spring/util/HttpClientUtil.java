package org.sun.spring.util;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Map;

import static org.sun.spring.util.JsonUtil.beanToJson;
import static org.sun.spring.util.RestClient.getClient;

/**
 * Created by tianfei on 16/11/8.
 */
public class HttpClientUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * post请求
     *
     * @param url
     * @param formParams
     * @return
     */
    public static String doPost(final String url, final Map<String, String> formParams) {
        if (MapUtils.isEmpty(formParams)) {
            return doPost(url);
        }

        try {
            MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
            formParams.keySet().stream().forEach(key -> requestEntity.add(key, MapUtils.getString(formParams, key, "")));
            return RestClient.getClient().postForObject(url, requestEntity, String.class);
        } catch (Exception e) {
            LOGGER.error("POST请求出错：{}", url, e);
        }

        return StringUtils.EMPTY;
    }

    public static <T> String doPost(final String url,T t){
        HttpHeaders headers = buildHeaders();
        final String json = JsonUtil.beanToJson(t);
        HttpEntity<String> requestEntity = new HttpEntity<>(json,headers);
        try {
            ResponseEntity<String> responseEntity =
                    RestClient.getClient().postForEntity(url,requestEntity,String.class);
            if(responseEntity.getStatusCode().is2xxSuccessful()){
                return responseEntity.getBody();
            }
        } catch (Exception e) {
            LOGGER.error("POST请求出错：{}", url, e);
        }
        return StringUtils.EMPTY;
    }

    /**
     * post请求
     *
     * @param url
     * @return
     */
    public static String doPost(String url) {
        try {
            return RestClient.getClient().postForObject(url, HttpEntity.EMPTY, String.class);
        } catch (Exception e) {
            LOGGER.error("POST请求出错：{}", url, e);
        }

        return StringUtils.EMPTY;
    }

    /**
     * get请求
     *
     * @param url
     * @return
     */
    public static String doGet(final String url,final Object... urlVariables) {
        HttpHeaders headers = buildHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> entity = RestClient.getClient()
                .exchange(url, HttpMethod.GET,requestEntity,String.class,urlVariables);
            if(entity.getStatusCode().is2xxSuccessful()){
                return entity.getBody();
            }
            //return RestClient.getClient().getForObject(url, String.class, urlVariables);
        } catch (Exception e) {
            LOGGER.error("GET请求出错：{}", url, e);
        }

        return StringUtils.EMPTY;
    }

    public static String doGet(final String url,
                               final Map<String, Object> paramPairs,
                               final Object... urlVariables) {
        if (MapUtils.isEmpty(paramPairs)) {
            return doGet(url,urlVariables);
        }
        final String getUrl = url + "?" + getParamString(paramPairs);
        try {
            return RestClient.getClient().getForObject(getUrl, String.class,urlVariables);
        } catch (Exception e) {
            LOGGER.error("GET请求出错：{}", url, e);
        }
        return StringUtils.EMPTY;
    }

    public static void doDelete(final String url, final Object... urlVariables) {
        RestClient.getClient().delete(url, urlVariables);
    }

    public static void doDelete(final String url, final Map<String, Object> uriVariables) {
        RestClient.getClient().delete(url, uriVariables);
    }

    public static void doDelete(final String url) {
        RestClient.getClient().delete(url);
    }

    public static <T> void doPut(final String url,T t,Map<String, Object> uriVariables) {
        RestClient.getClient().put(url,t,uriVariables);
    }

    public static <T> void doPut(final String url, T t, Object... uriVariables) {
        RestClient.getClient().put(url,t,uriVariables);
    }

    private static String getParamString(Map<String, Object> map) {
        StringBuffer params = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String paramPair = entry.getKey() + "=" + entry.getValue().toString();
            if (StringUtils.isBlank(params)) {
                params.append(paramPair);
            } else {
                params.append("&" + paramPair);
            }
        }
        return params.toString();
    }

    private static HttpHeaders buildHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        //headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }


}
