package com.atguigu.gulimall.commons.exception;

public class LoginPasswordException extends RuntimeException {

    public LoginPasswordException() {
        super("密码错误，请重新输入！！！");
    }
}
