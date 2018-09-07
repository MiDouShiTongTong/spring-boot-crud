package com.yyc.sb.springboot04web.exception;

public class UserNotExistException extends MyException {
    public UserNotExistException() {
        super("001", "用户不存在");
    }
}
