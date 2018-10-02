package com.springboot.demo001.service;

import com.springboot.demo001.dto.LoginBean;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean isValidUser(LoginBean loginBean) {
        return true;
    }


}
