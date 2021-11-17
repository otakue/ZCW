package com.otaku.mvc.controller;

import com.otaku.pojo.Admin;
import com.otaku.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Otaku
 * @date 2021/10/17-6:45
 */
@Controller
public class TestController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("test/ssm.html")
    public String getAll(HttpServletRequest request){
        List<Admin> adminList = adminService.getAll();
        request.setAttribute("adminList", adminList);
        System.out.println("----->");
        return "success";
    }
    @RequestMapping("send/array.html")
    @ResponseBody
    public String getRecire(@RequestBody List<Integer> list){
        System.out.println(list);
        return "success";
    }
    @RequestMapping("error")
    public void error(){
        int a=10/0;
        System.out.println(a);
    }

}
