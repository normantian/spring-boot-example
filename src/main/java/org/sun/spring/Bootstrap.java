package org.sun.spring;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.sun.spring.shiro.QuickStart;

import java.util.HashMap;
import java.util.Map;

//@EnableAutoConfiguration //启用自动配置 该框架就能够进行行为的配置，以引导应用程序的启动与运行, 根据导入的starter-pom 自动加载配置
//@EnableAspectJAutoProxy()
//@ComponentScan(basePackages = "org.sun.spring")
//@EnableAsync
@Configuration
@ControllerAdvice
@SpringBootApplication
@EnableCaching
public class Bootstrap implements CommandLineRunner {


    public static void main(String[] args) {

        //SpringApplication.run(new Object[] {Bootstrap.class}, args);


//		app.setBannerMode(Banner.Mode.OFF);
//		app.run(args);

		SpringApplication.run(Bootstrap.class, args);

//        SpringApplication app = new SpringApplication(Bootstrap.class);
//        ConfigurableApplicationContext context = app.run(args);
//        context.getBean(QuickStart.class).run2();
//        System.exit(0);
    }

    // Java EE应用服务器配置，
    // 如果要使用tomcat来加载jsp的话就必须继承SpringBootServletInitializer类并且重写其中configure方法
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Bootstrap.class);
//    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("springboot启动完成！");
    }

//	@Bean
//	public Realm realm() {
//		TextConfigurationRealm realm = new TextConfigurationRealm();
//		realm.setUserDefinitions("joe.coder=password,user\n" +
//				"jill.coder=password,admin");
//
//		realm.setRoleDefinitions("admin=read,write\n" +
//				"user=read");
//		realm.setCachingEnabled(true);
//		return realm;
//	}

    /**
     * Example hard coded Realm bean.
     *
     * @return hard coded Realm bean
     */
//	@Bean
//	public Realm realm() {
//		TextConfigurationRealm realm = new TextConfigurationRealm();
//		realm.setUserDefinitions("joe.coder=password,user\n" +
//				"jill.coder=password,admin");
//
//		realm.setRoleDefinitions("admin=read,write\n" +
//				"user=read");
//		realm.setCachingEnabled(true);
//		return realm;
//	}
//    @ExceptionHandler(AuthorizationException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ResponseEntity<Map> handleException(AuthorizationException e) {
//
//        // you could return a 404 here instead (this is how github handles 403, so the user does NOT know there is a
//        // resource at that location)
//        //log.debug("AuthorizationException was thrown", e);
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("status", HttpStatus.FORBIDDEN.value());
//        map.put("message", e.getLocalizedMessage());
////        model.addAttribute("errors", map);
//
//        return new ResponseEntity<Map>(map,HttpStatus.FORBIDDEN);
//    }

}
