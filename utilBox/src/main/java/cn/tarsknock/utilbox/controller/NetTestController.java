package cn.tarsknock.utilbox.controller;

import cn.tarsknock.utilbox.service.NetTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/net")
public class NetTestController {

    @Autowired
    private NetTestService netTestService;

    @GetMapping("ping")
    public Integer ping(HttpServletRequest request){
        String ip = netTestService.pingServer(request);
        log.info("ip:{}, has connected to internet!", ip);
        return 666;
    }
}
