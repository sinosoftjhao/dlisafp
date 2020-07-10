package com.dlis.afp;

import com.dlis.afp.afp.jdbc.OracleClient;
import com.dlis.afp.afp.rocketmq.RocketConsumer;
import com.dlis.afp.afp.zookeeper.ZkClient;
import com.dlis.afp.afp.rocketmq.RockProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AfpApplicationTests {
    @Autowired
    OracleClient oracleClient;
    @Autowired
    ZkClient zkClient;
    @Autowired
    RockProducer rockProducer;
    @Autowired
    RocketConsumer rocketConsumer;

    @Test
    void contextLoads() {
    }

    @Test
    void sqlTest() {
        oracleClient.exeSql("insert into a (aa) values ('22')");
    }


    @Test
    void zkNode() {
        zkClient.createNode("/xxxx", "xxxxx");
    }

    @Test
    void sendMessage() throws MQClientException {
        for (int i = 0; i < 10; i++) {
            rockProducer.sendSync("202x007011" + i, "azzzaa", "cccbbb");
        }
    }

    @Test
    void sendAsync(){
        for (int i = 0; i < 10; i++) {
            rockProducer.sendAsync("202011" + i, "azzzaa", "cccbbb");
        }
    }

    @Test
    void sendOrder() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        List<StringBuffer> mesList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            StringBuffer content = new StringBuffer("订单创建 : " + i);
            StringBuffer content1 = new StringBuffer( "订单支付 : " + i);
            StringBuffer content2 = new StringBuffer("订单完成: " + i);
            rockProducer.sendOrder("taobao1", "", content.toString(),i);
            rockProducer.sendOrder("taobao1", "", content1.toString(),i);
            rockProducer.sendOrder("taobao1", "", content2.toString(),i);
        }
    }

    @Test
    void pushOrder() throws MQClientException {
        rocketConsumer.consumerOrder();
    }

}
