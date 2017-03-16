package org.sun.spring.config;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.sun.spring.shiro.AuthcFilter;
import org.sun.spring.shiro.RoleAuthzFilter;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by tianfei on 17/3/14.
 */
//@Configuration
public class ShiroConfig2 {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Bean(name = "filterShiroFilterRegistrationBean")
    @ConditionalOnMissingBean
    public FilterRegistrationBean filterShiroFilterRegistrationBean() throws Exception {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter((AbstractShiroFilter) shiroFilterFactoryBean().getObject());
        filterRegistrationBean.setOrder(1);

        return filterRegistrationBean;
    }

//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        DelegatingFilterProxy httpBasicFilter = new DelegatingFilterProxy();
//        registrationBean.setFilter(httpBasicFilter);
//        Map<String,String> m = new HashMap<>();
//        m.put("targetBeanName","logFilter");
//        m.put("targetFilterLifecycle","false");
//        registrationBean.setInitParameters(m);
//        List<String> urlPatterns = new ArrayList<>();
//        urlPatterns.add("/*");
//        registrationBean.setUrlPatterns(urlPatterns);
//        return registrationBean;
//        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
//        filterRegistration.setFilter(shi);
        //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
//        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
//        filterRegistration.setEnabled(true);
//        filterRegistration.setOrder(3);
//        filterRegistration.addUrlPatterns("/*");// 可以自己灵活的定义很多，避免一些根本不需要被Shiro处理的请求被包含进来
//        return filterRegistration;
//    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * <p>
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * <p>
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * <p>
     * <p>
     * <p>
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * <p>
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager

        shiroFilterFactoryBean.setSecurityManager(securityManager());

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面

//        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接

//        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权界面;

//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        //自定义拦截器

        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //限制同一帐号同时在线的个数。
//        filtersMap.put("kickout", kickoutSessionControlFilter());
        filtersMap.put("authc", new AuthcFilter());
        filtersMap.put("roles", new RoleAuthzFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        // 权限控制map.

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断

        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了

        // 从数据库获取动态的权限

        // filterChainDefinitionMap.put("/add", "perms[权限添加]");

        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;

        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->

        //logout这个拦截器是shiro已经实现好了的。

        // 从数据库获取

//        List<SysPermissionInit> list = sysPermissionInitService.selectAll();
//
//        for (SysPermissionInit sysPermissionInit : list) {
//            filterChainDefinitionMap.put(sysPermissionInit.getUrl(),
//                    sysPermissionInit.getPermissionInit());
//        }
        filterChainDefinitionMap.put("/shiroDemo/login", "anon");
//        filterChainDefinitionMap.put("/shiroDemo/logout", "logout");
//        filterChainDefinitionMap.put("/shiroDemo/**", "roles[admin]");

//        filterChainDefinitionMap.put("/shiroDemo/test", "roles[admin]");
        filterChainDefinitionMap.put("/shiroDemo/**", "authc");


        shiroFilterFactoryBean
                .setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    /**
     * 身份认证realm
     */
    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
    public Realm realm() {
        PropertiesRealm propertiesRealm = new PropertiesRealm();
        propertiesRealm.init();
        propertiesRealm.setCachingEnabled(true);
        return propertiesRealm;
    }

//    @Bean
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
////        chainDefinition.addPathDefinition("/login.html", "authc"); // need to accept POSTs from the login form
////        chainDefinition.addPathDefinition("/logout", "logout");
//        chainDefinition.addPathDefinition("/","anon");
//        chainDefinition.addPathDefinition("/shiro/**","authc");
//        return chainDefinition;
//    }

//    @Bean(name = "lifecycleBeanPostProcessor")
//    @ConditionalOnMissingBean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(realm());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis

        securityManager.setSessionManager(sessionManager());
        //rememberMe
        securityManager.setRememberMeManager(rememberMeManager());

        return securityManager;
    }

//    @Bean
////    @DependsOn("lifecycleBeanPostProcessor")
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
//        return defaultAdvisorAutoProxyCreator;
//    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(30 * 24 * 60 * 60);
        return simpleCookie;
    }

    /**
     * 配置shiro redisManager
     *
     * @return
     */
    public RedisManager redisManager() {
        System.out.println(host + ":" + port);
        RedisManager redisManager = new RedisManager();
//        System.out.println(properties.getPort());
//        redisManager.setHost("localhost");
//        redisManager.setPort(6379);
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setExpire(1800);// 配置缓存过期时间

        // redisManager.setTimeout(timeout);

        // redisManager.setPassword(password);

        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     *
     * @return
     */
//    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * Session Manager
     */
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        //设置seesion有效时间

        sessionManager.setGlobalSessionTimeout(1800000);
        return sessionManager;
    }


    /**
     * cookie管理对象;记住我功能
     *
     * @return
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        //cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)

        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
//    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
//        return new DefaultAdvisorAutoProxyCreator();
    }

//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager);
//        return advisor;
//    }

}
