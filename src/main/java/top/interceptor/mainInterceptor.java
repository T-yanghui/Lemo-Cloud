package top.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: SpringMVC_template
 * @description: 拦截器——登录后才能访问资源，否则转发到login.jsp
 * @author: T_yang
 * @create: 2021-07-09 08:14
 **/
public class mainInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // /tologin或者post to login放行
        String url = request.getRequestURI();
        if(url.indexOf("elfinder")>=0) {
            //判断用户是否登录
            Object obj = request.getSession().getAttribute("user");
            if (obj == null) {
                request.setAttribute("msg", "请先登录...");
                request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
