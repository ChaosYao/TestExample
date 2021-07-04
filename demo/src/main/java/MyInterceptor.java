/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2021 All Rights Reserved.
 */

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author leping
 * @version $Id: MyInterceptor.java, v 0.1 2021-07-04 下午7:03 leping Exp $$
 */

public class MyInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if("GET".equalsIgnoreCase(request.getMethod())) {
            //TODO xxx
        }
        System.out.println("preHandle");
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        System.out.println("requestUri：" + requestUri);
        System.out.println("contextPath：" + contextPath);
        System.out.println("url：" + url);
        String username = (String) request.getSession().getAttribute("username");
        System.out.println("username: " + username);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
        if(modelAndView != null) {
            Map<String, String> map = new HashMap<String, String>();
            modelAndView.addAllObjects(map);
        }
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
