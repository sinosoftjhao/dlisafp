package com.dlis.afp.afp.jmsmq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;


@Service
public class ProductMq  {

     @Autowired
     private JmsTemplate jmsTemplate;

     public void sendMessage(Destination destination, final String message){
         jmsTemplate.convertAndSend(destination, message);
     }
}