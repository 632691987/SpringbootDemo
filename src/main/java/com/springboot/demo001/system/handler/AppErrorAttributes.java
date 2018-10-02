package com.springboot.demo001.system.handler;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 *
 * 这个类的作用是说， 当仅仅用 POSTMAN 去调用 restful 的时候出现了错误，就使用这个类去添加 map, 添加 JSON 属性
 * 而这个类之所以能够使用，是因为它 extends DefaultErrorAttributes
 *
 *
 */
@Component
public class AppErrorAttributes extends DefaultErrorAttributes {

    public static final String UserNotExistExceptionAttribute = "UserNotExistExceptionAttribute";

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        Map<String, Object> customizeErrorMap = (Map<String, Object>)webRequest.getAttribute(UserNotExistExceptionAttribute, RequestAttributes.SCOPE_REQUEST);
        //map.putAll(customizeErrorMap);
        map.put(UserNotExistExceptionAttribute, customizeErrorMap);
        return map;
    }


}
