package top.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import top.entity.User;
import top.service.MailSenderService;
import top.service.UserService;
import top.utils.verificationCodeGenerator;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @program: SpringMVC_template
 * @description: 注册控制器
 * @author: T_yang
 * @create: 2021-07-14 04:23
 **/
@Controller
public class RegisterController {
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private verificationCodeGenerator verificationCodegenerator;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/toregister")
    public String toRegister(){
        return "register";
    }

    @RequestMapping(value = "/registerVerification")
    public void RegisterVer(String username, HttpSession session, HttpServletResponse response) throws IOException {
        if(userService.getuserByname(username)!=null){
               response.getWriter().print("user already exists");
        }else{
            String verificationCode = verificationCodegenerator.generate();
            mailSenderService.sendTextMail(username,"注册验证码",verificationCode);
            session.setAttribute("verificationCode",verificationCode);
            response.getWriter().print("Send Success");
        }
    }
    @RequestMapping(value = "/register")
    public String Register(User user, String verificationCode, Model model, HttpSession session){
        String code = (String)session.getAttribute("verificationCode");
        if(code.equals(verificationCode)){
            userService.saveUser(user);
            model.addAttribute("msg","register success");
            return "login";
        }
        model.addAttribute("msg","verification code error");
        model.addAttribute("username",user.getUsername());
        return "register";
    }
}
