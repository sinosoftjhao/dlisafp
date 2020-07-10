package com.example.studyclient;

import com.example.studyclient.client.CxfClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudyclientApplicationTests {

    @Autowired
    CxfClient cxfClient;
    @Test
    void contextLoads() throws Exception {
        String message =(String) cxfClient.axisClient("阿里巴巴");
        System.out.println(message);
    }
    @Test
    void jarClient(){
        cxfClient.jarClient("腾讯");
    }
    @Test
    void cxfDyClient2() throws Exception {
        cxfClient.cxfDyClient2("中科软");
    }

}
