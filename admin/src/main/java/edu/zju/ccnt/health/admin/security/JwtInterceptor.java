package edu.zju.ccnt.health.admin.security;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println();
        if(request.getMethod().equals("OPTIONS")) return true;//预请求不检查Token
        String authHeader = request.getHeader("Authorization");
        System.out.println(authHeader);
//
        if (authHeader == null ||!authHeader.startsWith("Bearer ") ) {
            throw new ServletException("invalid Authorization header");
        }
        //取得token
        String token = authHeader.substring(7);
        try {
            JwtUtil.checkToken(token);
            return true;
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
}
