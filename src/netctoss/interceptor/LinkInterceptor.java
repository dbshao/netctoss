package netctoss.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by itachi on 2017/1/20.
 */
public class LinkInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, Object o) throws Exception {
        HttpSession session= httpServletRequest.getSession();
        Object obj=session.getAttribute("admin");
        if(obj==null){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login/tologin.do");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, Object o, Exception e) throws Exception {

    }
}
