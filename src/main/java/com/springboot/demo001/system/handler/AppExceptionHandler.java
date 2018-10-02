package com.springboot.demo001.system.handler;

import com.springboot.demo001.system.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler {

    /**
     *
     *
     *
     * 这种不是自适应的， POSTMAN 和 CHROME 得到同样效果
     *
     *
     *
     */
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String, Object> handlerUserNotExistException(Exception e) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("customize", "user not exist");
//        map.put("message", e.getMessage());
//        return map;
//    }

    /**
     *
     *
     *
     * 这竟然能够是自适应效果，主要核心关键是:
     * request.setAttribute("javax.servlet.error.status_code", 400);
     * 或者
     * request.setAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE, 400);
     *
     *
     *
     */
    @ExceptionHandler(UserNotExistException.class)
    public String handlerUserNotExistException(UserNotExistException exception, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("customize", "this is customize message, value inserted");
        map.put("message", exception.getMessage());

        //一定要传入错误状态码
        request.setAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE, HttpStatus.EXPECTATION_FAILED.value());

        //这个是为了传输到 AppErrorAttributes 类的 getErrorAttributes 函数
        request.setAttribute(AppErrorAttributes.UserNotExistExceptionAttribute, map);

        /**
         * 注意了，这个/error, 是hardcode 在代码里面的。然后，究竟是什么 html, 就看那个
         * request.setAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE, HttpStatus.EXPECTATION_FAILED.value());
         * 在这个页面看来，是417, 所以会自动去 /error/417.html
         */
        return "forward:/error";
    }

}
