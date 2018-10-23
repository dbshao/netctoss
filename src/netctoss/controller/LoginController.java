package netctoss.controller;

import netctoss.entity.Admin;
import netctoss.service.ApplicationException;
import netctoss.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by itachi on 2017/1/18.
 */

@Controller
@RequestMapping("/login")
public class LoginController {
    private LoginService loginService;

    public LoginService getLoginService() {
        return loginService;
    }

    @Resource(name="loginService")
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("tologin.do")
    public String toLogin(){
        return "login/login";
    }

    @RequestMapping("/login.do")
    public ModelAndView checkLogin(String adminCode, String password, HttpSession session){
//        try {
            Admin admin=loginService.login(adminCode,password);
            session.setAttribute("admin",admin);
/*        } catch (Exception e) {
            e.printStackTrace();
            if(e instanceof ApplicationException){
                //应用异常,提示用户
                Map<String,String> map=new HashMap<String,String>();
                //用户名回显
                map.put("adminCode",adminCode);
                map.put("message",e.getMessage());
                return new ModelAndView("login/login",map);
            }
            //系统异常，提示用户稍后尝试
            return new ModelAndView("error");
        }
        */
        //没有异常，重定向至首页
        return new ModelAndView(new RedirectView("toIndex.do"));
    }

    //转发至首页
    @RequestMapping("toIndex.do")
    public String toIndex(){
        return "login/index";
    }

    @ExceptionHandler
    public String execute (HttpServletRequest req,Exception e) throws Exception {
        //应用异常，提示用户
        if(e instanceof ApplicationException){
            //回显
            req.setAttribute("adminCode",req.getParameter("adminCode"));
            req.setAttribute("message",e.getMessage());
            return "/login/login";
        }else{
//            系统异常交给Spring
            throw e;
        }
    }








}
