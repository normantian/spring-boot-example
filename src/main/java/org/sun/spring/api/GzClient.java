package org.sun.spring.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * 广仲http client
 * Created by tianfei on 16/11/12.
 */
//@Component
public class GzClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(GzClient.class);

    protected String serverUrl;
    protected int port;
    protected String consumerKey;
    protected String consumerSecret;
    protected String userName;
    protected String password;
    private static String accessToken;
    private static String refreshToken;
    private final String baseUrl;


    public GzClient(String _serverUrl, int _port, String _accessToken) {
        this.serverUrl = _serverUrl;
        this.port = _port;
        accessToken = _accessToken;
        baseUrl = _serverUrl + ":" + _port + "/";
    }

    public GzClient(String serverUrl,
                    int port,
                    String consumerKey,
                    String consumerSecret,
                    String userName,
                    String password) {
        this.serverUrl = serverUrl;
        this.port = port;
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.userName = userName;
        this.password = password;
        baseUrl = serverUrl + ":" + port + "/";
    }

    /**
     * 刷新access token
     */
    public void refreshAccessToken() {
        System.out.println("refresh token");
        accessToken = "new token "+ System.nanoTime();
        System.out.println(accessToken);
    }

    /***
     * TODO HttpClientUtil 需要替换成restTemplate
     * 获取access token
     */
    @PostConstruct
    public void createAccessToken() {
        System.out.println("create token");
        accessToken = "create token " + System.nanoTime();
        System.out.println(accessToken);
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

}
