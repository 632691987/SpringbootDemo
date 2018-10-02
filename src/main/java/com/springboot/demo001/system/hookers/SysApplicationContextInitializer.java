package com.springboot.demo001.system.hookers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SysApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("===========================================");
        System.out.println("SysApplicationContextInitializer.initialize");
        System.out.println("===========================================");
    }


}
