package top.controller;

/**
 * @program: SpringMVC_template
 * @description: 处理用户忘记密码
 * @author: T_yang
 * @create: 2021-07-11 03:47
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.entity.User;
import top.service.MailSenderService;
import top.service.UserService;
import top.utils.verificationCodeGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class ForgetController {
   @Autowired
   private MailSenderService mailSenderService;
   @Autowired
   private verificationCodeGenerator verificationCodegenerator;
   @Autowired
   private UserService userService;
   @RequestMapping(value = "/toforget")
    public String toforget(){

       return "forgot";
   }

  @RequestMapping(value = "/forget")
    public String forget(String username, HttpSession session, Model model){
       User res = userService.getuserByname(username);
       String msg;
       if(res!=null) {
           String verificationCode = verificationCodegenerator.generate(6);
           mailSenderService.sendTextMail(username,"WebFiles验证","验证码："+ verificationCode);
           session.setAttribute("verificationCode",verificationCode);
//           msg="send verification code success";
//           model.addAttribute("msg",msg);
           return "reset";
       }
       model.addAttribute("msg","用户不存在");
       return "forgot";
  }

  @RequestMapping(value = "/forgetmailSend")
  public void forgetmailSender(String username, HttpSession session, HttpServletResponse response) throws IOException {
      if(userService.getuserByname(username)!=null){
          String verificationCode = verificationCodegenerator.generate();
          mailSenderService.sendTextMail(username,"注册验证码",verificationCode);
          session.setAttribute("verificationCode",verificationCode);
          response.getWriter().print("Send Success");
      }else{
          response.getWriter().print("user do not exist");
      }
  }
  @RequestMapping(value = "/forgetVer")
  public String forgetVer(String username, String verificationCode, HttpSession session, Model model) throws IOException, ServletException {
       String code = (String)session.getAttribute("verificationCode");
       if(code.equals(verificationCode)){
           session.setAttribute("username",username);
           return "reset";
       }else{
           model.addAttribute("msg","Verification error");
           model.addAttribute("username",username);
           return "forgot";
       }
  }
  @RequestMapping(value = "/reset",method = RequestMethod.POST)
    public String reset(HttpSession session,HttpServletRequest request,HttpServletResponse response,Model model,String password) throws ServletException, IOException {
       String username = (String)session.getAttribute("username");
       String msg;
       if(username!=null) {
           try {
               userService.resetUser(username, password);
               msg="modified success";
               model.addAttribute("msg",msg);
               return "login";
           }catch (Exception e){
               msg="reset error";
           }
       }
      msg="reset error";
      model.addAttribute("msg",msg);
      return "forget";
  }
}
