package com.otaku.mvc.config;

import com.google.gson.Gson;
import com.otaku.utils.AccessForbiddenException;
import com.otaku.utils.CrowdUtil;
import com.otaku.utils.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import com.otaku.utils.loginfailexception;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Otaku
 * @date 2021/10/18-21:10
 */
@ControllerAdvice
public class error {
    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView resolveMathException(HttpServletRequest request,HttpServletResponse response,Exception e) throws IOException {
        String view="error/error";
        return commonResolve(view, request, response, e);
    }
    @ExceptionHandler(loginfailexception.class)
    public ModelAndView loginfaildException(HttpServletRequest request,HttpServletResponse response,Exception e) throws IOException {
        String view="login/admin-login";
        return commonResolve(view,request,response,e);
    }
    @ExceptionHandler(AccessForbiddenException.class)
    public ModelAndView authorizationLoginFail(HttpServletRequest request,HttpServletResponse response,Exception e) throws IOException {
        String view="login/admin-login";
        return commonResolve(view,request,response,e);
    }

    /**
     * 错误页面公共类
     * @param viewName
     * @param request
     * @param response
     * @param e
     * @return
     * @throws IOException
     */
    public ModelAndView commonResolve(String viewName,HttpServletRequest request, HttpServletResponse response,Exception e) throws IOException {
        //调用工具类判断是否是ajax请求
        boolean requestType = CrowdUtil.judgeRequestType(request);
        if (requestType){
            //使用工具类获取失败信息
            ResultEntity<Object> failed = ResultEntity.failed(e.getMessage());
            Gson gson = new Gson();
            //将ResultEntity对象转换为json
            String json = gson.toJson(failed);
            response.getWriter().write(json);
            return null;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
