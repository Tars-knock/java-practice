package cn.tarsknock.utilbox;

import cn.tarsknock.utilbox.service.impl.AliDnsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UtilBoxApplicationTests {
    @Autowired
    private AliDnsService aliDnsService;
    @Test
    void contextLoads() {
    }
    @Test
    void testList(){
        aliDnsService.getDnsList();
    }

}
