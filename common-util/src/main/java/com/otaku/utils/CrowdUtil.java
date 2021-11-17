package com.otaku.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Otaku
 * @date 2021/10/18-20:20
 */
public class CrowdUtil {
    /**
     * 判断当前请求是否是ajax请求
     * @param request
     * @return
     */
    public static boolean judgeRequestType(HttpServletRequest request){
        String accept = request.getHeader("Accept");
        String XRequested = request.getHeader("X-Requested-With");
        return ((accept !=null && accept.contains("application/json"))
                    || (XRequested != null && XRequested.equals("XMLHttpRequest")));
    }
}
