package com.springboot.demo001.system.hookers;

import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

@Component
public class SysApplicationRunListner implements SpringApplicationRunListener {

    @Override
    public void starting() {
        System.out.println("===========================================");
        System.out.println("com.springboot.demo001.system.hookers.SysApplicationRunListner.starting");
        System.out.println("===========================================");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("===========================================");
        System.out.println("com.springboot.demo001.system.hookers.SysApplicationRunListner.environmentPrepared");
        System.out.println("===========================================");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("===========================================");
        System.out.println("com.springboot.demo001.system.hookers.SysApplicationRunListner.contextPrepared");
        System.out.println("===========================================");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("===========================================");
        System.out.println("com.springboot.demo001.system.hookers.SysApplicationRunListner.contextLoaded");
        System.out.println("===========================================");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("===========================================");
        System.out.println("com.springboot.demo001.system.hookers.SysApplicationRunListner.started");
        System.out.println("===========================================");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("===========================================");
        System.out.println("com.springboot.demo001.system.hookers.SysApplicationRunListner.running");
        System.out.println("===========================================");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("===========================================");
        System.out.println("com.springboot.demo001.system.hookers.SysApplicationRunListner.failed");
        System.out.println("===========================================");
    }
}
