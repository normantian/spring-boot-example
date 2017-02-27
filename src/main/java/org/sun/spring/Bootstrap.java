package org.sun.spring;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAutoConfiguration //启用自动配置 该框架就能够进行行为的配置，以引导应用程序的启动与运行, 根据导入的starter-pom 自动加载配置
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "org.sun.spring")
public class Bootstrap {

	public static void main(String[] args) {

		//SpringApplication.run(new Object[] {Bootstrap.class}, args);

		SpringApplication app = new SpringApplication(Bootstrap.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

}
