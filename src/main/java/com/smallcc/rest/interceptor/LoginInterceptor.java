package com.smallcc.rest.interceptor;

import com.smallcc.rest.pojo.Const;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//
///**
// * 登陆拦截处理
// * create by smallcc 2018/11/19
// */
//public class LoginInterceptor extends HandlerInterceptorAdapter {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        String path = request.getServletPath();
//        if (path.matches(Const.NO_INTERCEPTOR_PATH)) {
//            //不需要的拦截直接过
//            return true;
//        } else {
//            // 这写你拦截需要干的事儿，比如取缓存，SESSION，权限判断等
//            String user = request.getParameter("user");
//            if(user == null ){
//                System.out.println("==============login error=================");
//                return false;
//            }
//            System.out.println("==================login success==================");
//            return true;
//        }
//    }
//}