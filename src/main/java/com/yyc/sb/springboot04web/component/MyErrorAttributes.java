package com.yyc.sb.springboot04web.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/*
    JSON错误数据处理器
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        // Spring Boot默认的JSON信息
        Map<String, Object> map =  super.getErrorAttributes(webRequest, includeStackTrace);

        // 获取自定义全局异常处理器中的对象
        Map<String, Object> myException = (Map<String, Object>) webRequest.getAttribute("myException", 0);

        // 向Spring Boot默认返回的JSON数据中添加数据
        map.put("myException", myException);
        return map;
    }
}
