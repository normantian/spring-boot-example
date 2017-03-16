package org.sun.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by chenbin on 8/5/16.
 */
//@Configuration
//@EnableWebMvc
//@EnableSwagger2
//@ComponentScan("org.sun.spring.controller")
public class SwaggerConfig {//extends WebMvcConfigurerAdapter {
    private static final String TITLE = "无讼对接平台API接口";

    private static final String DESCRIPTION = "无讼对接平台后台API接口文档";

    private static final String VERSION = "1.0";

    private static final String TERMS_OF_SERVICE = "My terms of service";

    private static final String LICENSE_TYPE = "My License";

    private static final String LICENCE_URL = "My Apps API License URL";

    private static final Contact CONTACT = new Contact("norman", "", "tianfei@wusongtech.com");


    /**
     * Every Docket bean is picked up by the swagger-mvc framework - allowing for multiple
     * swagger groups i.e. same code base multiple swagger resource listings.
     */
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                TITLE,
                DESCRIPTION,
                VERSION,
                TERMS_OF_SERVICE,
                CONTACT,
                LICENSE_TYPE,
                LICENCE_URL
        );
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//    }

}
