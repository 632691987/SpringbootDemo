package com.springboot.demo001.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ThymeleafController {

    @GetMapping("thymeleafResult01")
    public String thymeleafTestResult01(Map<String, Object> model) {
        model.put("hello","This is hello message");
        return "result01";
    }

    @GetMapping("thymeleafResult02")
    public String thymeleafTestResult02(Map<String, Object> model) {
        return "result02";
    }

}