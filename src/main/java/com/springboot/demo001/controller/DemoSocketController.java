package com.springboot.demo001.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoSocketController {

    @GetMapping("/testTextWebSocket")
    public String CommunicatePage() {
        return "socket/textWebSocket";
    }

}
