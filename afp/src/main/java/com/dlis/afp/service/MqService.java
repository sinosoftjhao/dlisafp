package com.dlis.afp.service;


import com.dlis.afp.afp.jmsmq.ProductMq;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.stereotype.Service;

import javax.jms.Destination;


@Service
public class MqService {

    private final static Logger logger = LoggerFactory.getLogger(MqService.class);

    @Autowired
    private ProductMq productMq;

    public String sendMessage(String quename,final String message){
        try{
            Destination destination = new ActiveMQQueue(quename);
            productMq.sendMessage(destination,message);
        }catch (JmsException e){
            logger.error("mq操作失败",e);
            return "fail";
        }
        return "success";
    }
}
