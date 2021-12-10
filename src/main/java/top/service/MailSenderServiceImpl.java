package top.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @program: SpringMVC_template
 * @description: 邮件发送服务
 * @author: T_yang
 * @create: 2021-07-11 00:15
 **/
@Service
public class MailSenderServiceImpl implements MailSenderService{
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    SimpleMailMessage mailMessage;

    @Override
    public void sendTextMail(String to, String subject, String content) {
        mailMessage.setTo(to);//接受者
        mailMessage.setSubject(subject);//主题
        mailMessage.setText(content);//邮件内容

        javaMailSender.send(mailMessage);
    }
}
