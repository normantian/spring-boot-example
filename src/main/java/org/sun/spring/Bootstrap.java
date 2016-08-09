package org.sun.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAutoConfiguration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "org.sun.spring")
public class Bootstrap {

	public static void main(String[] args) {
		SpringApplication.run(new Object[] {Bootstrap.class}, args);
	}

}
