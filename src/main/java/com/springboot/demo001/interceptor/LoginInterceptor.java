package com.springboot.demo001.interceptor;

import com.springboot.demo001.dto.LoginBean;
import com.springboot.demo001.service.LoginService;
import com.springboot.demo001.system.AppConst;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if(Objects.isNull(user)) {

            //以下是专门处理给 RESTFul API 使用的，在实战中可以删除，begin
            String headerUserName = request.getHeader("username");
            String headerPassword = request.getHeader("password");
            if(!Strings.isNullOrEmpty(headerUserName) && !Strings.isNullOrEmpty(headerPassword)) {
                return service.isValidUser(new LoginBean(headerUserName, headerUserName));
            }
            //以下是专门处理给 RESTFul API 使用的，在实战中可以删除，end


            if(HttpMethod.valueOf(request.getMethod()) == HttpMethod.GET) {
                String fullURL = getFullURL(request);
                request.getSession().setAttribute(AppConst.SESSION_BEFORE_LOGIN_URL, fullURL);
            }

            request.setAttribute("errorMessage", "No permission, please login first");
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        }
        return true;
    }

    public String getFullURL(HttpServletRequest request) {
        StringBuilder requestURL = new StringBuilder(request.getRequestURI());
        String queryString = request.getQueryString();
        if (queryString == null) {
            return requestURL.toString();
        } else {
            return requestURL.append('?').append(queryString).toString();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
