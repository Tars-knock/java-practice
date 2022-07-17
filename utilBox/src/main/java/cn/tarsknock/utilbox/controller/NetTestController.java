package cn.tarsknock.utilbox.controller;

import cn.tarsknock.utilbox.service.DnsService;
import cn.tarsknock.utilbox.service.NetTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/net")
public class NetTestController {

    @Autowired
    private NetTestService netTestService;
    @Autowired
    private DnsService aliDnsService;
    @Value("${dns.ali.domainName}")
    private String domainName;

    private String cache;

    @GetMapping("ping")
    public Integer ping(HttpServletRequest request){
        String ip = netTestService.pingServer(request);
        log.info("ip:{}, has connected to internet!", ip);
        return 666;
    }
    @GetMapping("updateDns")
    public String updateDns(HttpServletRequest request, @RequestParam String ip){
        if(ip!=null && ip.equals(cache)){
            log.info("a repeat ip:{}", ip);
            return "need`t update"+ip;
        }else if(ip!=null){
            cache = ip;
            if(aliDnsService.registerDns(ip, domainName)){
                log.info("update DDNS success! your new ip:{}", ip);
                return "success,"+ip;
            }else{
                log.info("update fail, check the log ip:{}", ip);
                return "fail!!";
            }
        }else{
            log.info("ip is null!!");
            return "ip is null!";
        }
    }

}
