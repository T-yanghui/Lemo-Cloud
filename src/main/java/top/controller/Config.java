package top.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.entity.User;
import top.utils.verificationCodeGenerator;
@Configuration
public class Config {
    @Bean
    public User user(){
        return new User();
    }
    @Bean
    public verificationCodeGenerator vertificationCodegenerator(){
        return new verificationCodeGenerator();
    }

}
