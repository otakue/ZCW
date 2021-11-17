package com.otaku.mvc.interceptor;

import com.otaku.pojo.Admin;
import com.otaku.utils.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Otaku
 * @date 2021/10/21-9:01
 */
public class loginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        HttpSession session = request.getSession();
        Admin loginAdmin = (Admin)session.getAttribute("loginAdmin");
        if (loginAdmin==null){
            throw new AccessForbiddenException("请登入后再操作");
        }
        return true;
    }
}
