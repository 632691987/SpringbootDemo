package com.springboot.demo001.controller;

import com.springboot.demo001.dto.LoginBean;
import com.springboot.demo001.system.AppConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ModelAttribute("local_language")
    public String modelLocalLanguage() {
        Locale locale = LocaleContextHolder.getLocale();
        return locale.toString();
    }

    @GetMapping("login")
    public String loginPage(HttpServletRequest request) {

        //判断之前是否已经登录
        if(!Objects.isNull(request.getSession().getAttribute("loginUser"))) {
            return "redirect:/main.html";
        }
        return "login/login";
    }

    @PostMapping("validate")
    public String validateLogin(@RequestParam String username, @RequestParam String password,
                                Map<String, Object> model, HttpServletRequest request, HttpSession session) {
        logger.info("username is {}", username);
        logger.info("password is {}", password);
        model.put("errorMessage", "this is error message");
        model.put("username", "this is error message");
        session.setAttribute("loginUser",username);
        //return "login/success";

        if(request.getSession().getAttribute(AppConst.SESSION_BEFORE_LOGIN_URL) != null) {
            return "redirect:" + request.getSession().getAttribute(AppConst.SESSION_BEFORE_LOGIN_URL).toString();
        }

        //防止表单重复提交，重定向到主页
        return "redirect:/main.html";
    }

//    @PostMapping("validate2")
//    public String validateLogin2(@Validated @ModelAttribute LoginBean loginBean, BindingResult result,
//                                 Map<String, Object> model, HttpSession session) {
//
//        if(result.hasErrors()) {
//            return "login/login";
//        }
//
//        return validateLogin(loginBean.getUsername(), loginBean.getPassword(), model, session);
//    }

}
