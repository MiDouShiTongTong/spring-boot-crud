package com.yyc.sb.springboot04web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class AccountController {
    //    @RequestMapping(value = "/account/signIn", method = {RequestMethod.POST})
    @PostMapping(value = "/admin/service/account/signIn")
    public String signIn(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         RedirectAttributes redirectAttributes,
                         HttpSession httpSession ) {
        if (!StringUtils.isEmpty(username) && password.equals("123456")) {
            // 登陆成功，保存登陆信息，跳转后台首页
            httpSession.setAttribute("username", username);
            return "redirect:/admin/dashboard";
        } else {
            // 登陆失败，跳转登陆页面
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin/account/signIn";
        }
    }
}
