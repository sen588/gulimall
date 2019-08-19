package com.atguigu.gulimall.commons.exception;

public class LoginUsernameException extends RuntimeException {

    public LoginUsernameException() {
        super("用户名不已存在！！！");
    }
}
