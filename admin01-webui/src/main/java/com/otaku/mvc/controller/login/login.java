package com.otaku.mvc.controller.login;

import com.otaku.pojo.Admin;
import com.otaku.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author Otaku
 * @date 2021/10/19-22:38
 */
@Controller
public class login {
    @Autowired
    private AdminService adminService;
    @RequestMapping("skip/admin/login.html")
    public ModelAndView skipLogin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/admin-login");
        return modelAndView;
    }
    @RequestMapping("admin/do/login.html")
    public String loginAdmin(@RequestParam("loginAcct") String userName,
                                   @RequestParam("userPswd") String password,
                                   HttpSession session){
        //验证账号密码是否正确
        Admin admin = adminService.getAdminByLoginAcct(userName, password);
        //如果账号密码正确，存储到session中
        session.setAttribute("loginAdmin", admin);
        return "redirect:/admin/to/main/page.html";
    }
    @RequestMapping("logout.html")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }
}
