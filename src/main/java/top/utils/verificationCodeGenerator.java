package top.utils;

/**
 * @program: SpringMVC_template
 * @description: 生成验证码
 * @author: T_yang
 * @create: 2021-07-11 07:06
 **/

public class verificationCodeGenerator {
    int default_n = 6;
    public String generate(int n){
        StringBuffer stringBuffer =new StringBuffer();
        for(int i=0;i<n;i++){
            int tmp = (int) (Math.random()*10);
            stringBuffer.append(tmp);
        }
        return stringBuffer.toString();
    }
    public String generate(){
        return generate(default_n);
    }
}
