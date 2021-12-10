package top.service;

/**
 * @program: SpringMVC_template
 * @description: mailSender接口
 * @author: T_yang
 * @create: 2021-07-11 00:20
 **/
public interface MailSenderService {
    /**
        * @Description //TODO to——接受邮箱 subject——主题 content——邮件内容
        * @Param [to, subject, content]
            **/
    void sendTextMail(String to,String subject,String content);
}
