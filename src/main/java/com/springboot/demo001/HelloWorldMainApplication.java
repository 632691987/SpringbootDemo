package com.springboot.demo001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


/**
 * Springboot 推荐给容器中添加组件的方式是 @Configuration
 */
@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class HelloWorldMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldMainApplication.class, args);
    }
}