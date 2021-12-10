package top.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.entity.User;
import top.service.MailSenderService;
import top.service.UserServiceImpl;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MailSenderService mailSenderService;

    /**
    * @Description //登录页面初始化
    * @Param [] 
        **/

    @RequestMapping(value = {"tologin","/"},method = RequestMethod.GET)
    public String tologin(){
        return "login";
    }
    /**
        * @Description //登录验证
        * @Param [user, request]
            **/

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user, HttpSession session, Model model){
        User res = userService.getuserByname(user.getUsername());
        String msg;
        if(res!=null) {
            if (res.getPassword().equals(user.getPassword())) {
                session.setAttribute("user", user);
                System.out.println(user + " 登录成功");
                return "redirect:elfinder";
            }
            msg="密码错误";
        }else{
            msg="用户名错误";
        }
        model.addAttribute("username",user.getUsername());
        model.addAttribute("msg",msg);
        return "login";
    }

    /**
        * @Description //返回主页面
        * @Param []
            **/
    @RequestMapping(value = "/elfinder",method = RequestMethod.GET)
    public String elfinder(HttpSession session,Model model){
        User user = (User)session.getAttribute("user");
        model.addAttribute("username",user.getUsername());
        return "elfinder";
    }
    /**
        * @Description //TODO 退出登录
        * @Param
            **/
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:tologin";
    }
}
