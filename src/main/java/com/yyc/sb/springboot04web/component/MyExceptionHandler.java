package com.yyc.sb.springboot04web.component;

import com.yyc.sb.springboot04web.exception.MyException;
import com.yyc.sb.springboot04web.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/*
    全局异常处理器
 */
@ControllerAdvice
public class MyExceptionHandler {

    // 自定义错误异常处理，浏览器和其他客户端的返回类型都为JSON
//    @ExceptionHandler(MyException.class)
//    @ResponseBody
//    public Map<String, Object> handlerException(MyException e) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("errorCode", e.getErrorCode());
//        map.put("errorMessage", e.getErrorMessage());
//        return map;
//    }

    // 自定义错误异常处理，浏览器返回类型为html页面，其他客户端返回类型为JSON
    @ExceptionHandler(MyException.class)
    public String handlerException(MyException e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("errorCode", e.getErrorCode());
        map.put("errorMessage", e.getErrorMessage());

        request.setAttribute("myException", map);
        request.setAttribute("javax.servlet.error.status_code", 500);
        return "forward:/error/";
    }
}
