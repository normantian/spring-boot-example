package com.example.config;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.AbstractShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import org.apache.shiro.mgt.SecurityManager;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by tianfei on 17/3/17.
 */
@Configuration
public class ShiroConfig {//extends AbstractShiroWebFilterConfiguration {

    @Value("${spring.redis.port}")
    private int port;
//    @Autowired
//    private SecurityManager securityManager;

    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
    public Realm realm() {
        PropertiesRealm propertiesRealm = new PropertiesRealm();
        propertiesRealm.init();
        propertiesRealm.setCachingEnabled(true);
        return propertiesRealm;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

//    @Bean
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//        filterChainDefinitionMap.put("/shiroDemo/login","anon");
//        //filterChainDefinitionMap.put("/shiroDemo/**","authc");
////        chainDefinition.addPathDefinition("/shiroDemo/**","authc");
////        chainDefinition.addPathDefinition("/shiroDemo/login","anon");
//        chainDefinition.addPathDefinitions(filterChainDefinitionMap);
//
//        return chainDefinition;
//    }

    @Bean
    public FormAuthenticationFilter formAuthenticationFilter(){
        return new AuthcFilter();
    }

    @Bean(name = "filterShiroFilterRegistrationBean")
    public FilterRegistrationBean filterShiroFilterRegistrationBean() throws Exception {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter((AbstractShiroFilter) shiroFilterFactoryBean().getObject());
        filterRegistrationBean.setOrder(1);

        return filterRegistrationBean;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

//        filterFactoryBean.setLoginUrl(loginUrl);
//        filterFactoryBean.setSuccessUrl(successUrl);
//        filterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //限制同一帐号同时在线的个数。
//        filtersMap.put("kickout", kickoutSessionControlFilter());
        filtersMap.put("authc", new AuthcFilter());
        filtersMap.put("roles", new RoleAuthzFilter());
        filterFactoryBean.setFilters(filtersMap);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        //filterChainDefinitionMap.put("/shiroDemo/login", "anon");
        //filterChainDefinitionMap.put("/shiroDemo/**", "authc");

        filterChainDefinitionMap.put("/shiroDemo/login","anon");
        filterChainDefinitionMap.put("/shiroDemo/**","authc");
//        filterChainDefinitionMap.put("/shiroDemo/test","roles[admin]");


        filterFactoryBean
                .setFilterChainDefinitionMap(filterChainDefinitionMap);

        filterFactoryBean.setSecurityManager(securityManager());
//        filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        System.out.println("Shiro拦截器工厂类注入成功!!");

        return filterFactoryBean;
    }

    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        //rememberMe
        securityManager.setRememberMeManager(rememberMeManager());

        return securityManager;
    }

    /**
     * cookie管理对象;记住我功能
     *
     * @return
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)

        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(30 * 24 * 60 * 60);
        return simpleCookie;
    }
}
