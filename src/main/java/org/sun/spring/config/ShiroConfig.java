package org.sun.spring.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by tianfei on 17/3/14.
 */
@Configuration
public class ShiroConfig {

    //    @Bean//(name = "realm")
////    @DependsOn("lifecycleBeanPostProcessor")
//    public PropertiesRealm realm(){
//        PropertiesRealm propertiesRealm = new PropertiesRealm();
//        propertiesRealm.init();
//        propertiesRealm.setCachingEnabled(true);
//        return propertiesRealm;
//    }

    /**

     * ShiroFilterFactoryBean 处理拦截资源文件问题。

     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在

     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager

     *

     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过

     * 3、部分过滤器可指定参数，如perms，roles

     *

     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面

        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接

        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权界面;

        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        //自定义拦截器

//        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //限制同一帐号同时在线的个数。

//        filtersMap.put("kickout", kickoutSessionControlFilter());
//        shiroFilterFactoryBean.setFilters(filtersMap);

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
        filterChainDefinitionMap.put("/shiroDemo/login","anon");
        filterChainDefinitionMap.put("/shiroDemo/**","authc");

        shiroFilterFactoryBean
                .setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }
    /**
     * 身份认证realm
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public Realm realm() {
        PropertiesRealm propertiesRealm = new PropertiesRealm();
        propertiesRealm.init();
        propertiesRealm.setCachingEnabled(true);
        return propertiesRealm;
//        TextConfigurationRealm realm = new TextConfigurationRealm();
//        realm.setUserDefinitions("joe.coder=password,user\n" +
//                "jill.coder=password,admin");
//
//        realm.setRoleDefinitions("admin=read,write\n" +
//                "user=read");
//        realm.setCachingEnabled(true);
//        return realm;
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


    //    @Bean(name = "securityManager")
//    public SecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(realm());
//        return securityManager;
//    }
    @Bean(name="lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());

        return securityManager;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

}
