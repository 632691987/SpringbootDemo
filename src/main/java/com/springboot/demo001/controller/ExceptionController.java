package com.springboot.demo001.controller;

import com.springboot.demo001.system.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * 这个controller 是专门实践 exception handler的
 */
@Controller
@RequestMapping("/error2")
public class ExceptionController {

    /**
     *   localhost:28081/error/usernotexist
     *   com.springboot.demo001.system.exception.UserNotExistException
     *   com.springboot.demo001.system.handler.AppExceptionHandler#handlerUserNotExistException
     *   com.springboot.demo001.system.handler.AppErrorAttributes
     *
     *   注意自适应效果
     */
    @GetMapping("usernotexist")
    public String handlerUserNotExist(HttpServletRequest request) {

        if(request!=null) {
            throw new UserNotExistException();
        }

        return "/login/login";
    }

}
