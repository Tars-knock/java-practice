package cn.tarsknock.utilbox.config;

import cn.tarsknock.utilbox.Exception.MyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = MyException.class)
    public String myExceptionHandler(MyException e){
//        System.out.println(e.getMessage());
        return "异常被拦截，服务器内部错误";
    }
}
