package top.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {
//    @ResponseStatus(value= HttpStatus.BAD_GATEWAY,reason="服务器异常")
    @ResponseBody
    @ExceptionHandler(MyException.class)
    public String handleMyException(MyException e){
        System.out.println("handleMyException");
        return e.getMessage();
    }
}
