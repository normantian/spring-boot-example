package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.thrift")
public class ThriftDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThriftDemoApplication.class, args);
	}
}
