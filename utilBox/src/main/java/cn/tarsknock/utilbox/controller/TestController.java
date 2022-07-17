package cn.tarsknock.utilbox.controller;

import cn.tarsknock.utilbox.Exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/error")
    public String errorTest(){
//        String input = request.getParameter("input");
        throw new MyException("这里是自定义的异常信息");
    }

    @GetMapping("/runtime")
    public void runtimeTest(){
        throw new RuntimeException("一个runtime exception");
    }
}
