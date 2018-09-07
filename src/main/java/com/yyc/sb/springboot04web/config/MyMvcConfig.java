package com.yyc.sb.springboot04web.config;

import com.yyc.sb.springboot04web.component.AdminSignInHandlerInterceptor;
import com.yyc.sb.springboot04web.component.MyLocaleResolver;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
/*
    实现WebMvcConfigurer接口来扩展Spring MVC组件
 */
//@EnableWebMvc
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 客户端发送/hello_view请求，请求到templates/success.html
        registry.addViewController("/hello_view").setViewName("success");
    }

    /*
        注册视图控制器

        1.直接返回WebMvcConfigurer也会起作用
        2.添加@Bean注解注入到组件中，没有注入组件配置不会生效
    */
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("sign-in");
                registry.addViewController("/admin/account/signIn").setViewName("sign-in");
                registry.addViewController("/admin/dashboard").setViewName("dashboard");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                /*
                    后台登陆验证拦截器
                        无需处理静态资源Spring Boot自动配置好了
                        登陆页面和登陆的接口不需要验证
                 */
                registry.addInterceptor(new AdminSignInHandlerInterceptor())
                        .addPathPatterns("/admin/**").excludePathPatterns("/admin/account/signIn", "/admin/service/account/signIn");
            }

        };
    }

    /*
        注册国际化组件
        方法名称必须为localeResolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
