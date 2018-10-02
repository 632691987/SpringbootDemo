package com.springboot.demo001.system.exception;

public class UserNotExistException extends RuntimeException {

    public UserNotExistException() {
        super("[Message]: user name not exist");
    }

}
