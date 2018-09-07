package com.yyc.sb.springboot04web.controller;

import com.yyc.sb.springboot04web.exception.UserNotExistException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(String user) {
        if (user.equals("123456")) {
            throw new UserNotExistException();
        }
        return "hello world";
    }
}
