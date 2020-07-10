package com.dlis.afp.afp.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
public class RocketConsumer {

    private final static Logger logger = LoggerFactory.getLogger(RocketConsumer.class);


    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;

    @Value("${rocketmq.consumer.groupName}")
    private String consumerGroup;

    private DefaultMQPushConsumer defaultMQPushConsumer = null;
//
//    @PostConstruct
//    public void init(){
//        defaultMQPushConsumer=new DefaultMQPushConsumer(consumerGroup);
//        defaultMQPushConsumer.setNamesrvAddr(namesrvAddr);
//        try {
//            // 订阅topic 名
//            defaultMQPushConsumer.subscribe("20200701", "*");
//            // 集群模式，消息只被消费一次
//            defaultMQPushConsumer.setMessageModel(MessageModel.CLUSTERING);
//            // 广播模式，消息重复消费
//            // defaultMQPushConsumer.setMessageModel(MessageModel.BROADCASTING);
//
//            defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
//
//                @Override
//                public ConsumeConcurrentlyStatus consumeMessage(
//                        List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                    for (MessageExt msg : msgs) {
//                        logger.info("Message Received: " + new String(msg.getBody()));
//                    }
//                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                }
//            });
//            defaultMQPushConsumer.start();
//        } catch (MQClientException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 有顺序的监听消费者
     * @throws MQClientException
     */
    @PostConstruct
    public void consumerOrder() throws MQClientException {
        defaultMQPushConsumer=new DefaultMQPushConsumer(consumerGroup);
        defaultMQPushConsumer.setNamesrvAddr(namesrvAddr);
        defaultMQPushConsumer.subscribe("taobao1","*");
        defaultMQPushConsumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt messageExt:
                list) {
                    logger.info("消费消息" + new String (messageExt.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        defaultMQPushConsumer.start();
    }

    @PreDestroy
    public void shutDownConsumer() {
        if (defaultMQPushConsumer != null) {
            defaultMQPushConsumer.shutdown();
        }
    }
}
