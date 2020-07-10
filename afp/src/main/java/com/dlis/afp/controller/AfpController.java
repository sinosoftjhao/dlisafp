package com.dlis.afp.controller;


import com.dlis.afp.afp.zookeeper.ZkClient;
import com.dlis.afp.service.MqService;
import com.dlis.afp.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
// Controller + ResponseBody = RestController
//@RestController
public class AfpController {

    private final static Logger logger = LoggerFactory.getLogger(AfpController.class);

    @Autowired
    private RedisService redisService;
    @Autowired
    private MqService mqService;
    @Autowired
    private ZkClient zkClient;
    @RequestMapping("redis.do")
    public String redis(String key, String value){
        String result = redisService.set(key,value);
        if("ok".equalsIgnoreCase(result)){
            logger.info("redis操作成功");
            return "login";
        }
        logger.error("redis操作失败");

        return "error";
    }

    @RequestMapping("activemq.do")
    public String activemq(String quaname, String message){
        String result = mqService.sendMessage(quaname,message);
        if(result.equalsIgnoreCase("success")){
            return "login";
        }
        return "error";
    }
    @RequestMapping("zk.do")
    public String zookeeper(String nodename, String value){
        boolean result = zkClient.createNode(nodename,value);
        if(result){
            return "login";
        }
        return "error";
    }
}
