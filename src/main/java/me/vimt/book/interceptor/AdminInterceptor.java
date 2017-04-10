package me.vimt.book.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/4/8 14:18
 * Description:
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {

    private static final String notLogin = "{\"code\": 2, \"msg\": \"您的权限不够\"}";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("admin") == null) {
            response.setContentType("application/json;charset=UTF-8");
            response.getOutputStream().write(notLogin.getBytes());
            response.setStatus(200);
            return false;
        }
        return true;
    }
}
