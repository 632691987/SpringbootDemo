package com.springboot.demo001.dto;

import javax.validation.constraints.Size;

public class LoginBean {

    public LoginBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginBean() {
    }

    @Size(min = 6, message="min is 6, you fucking stupid")
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
