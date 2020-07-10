package com.dlis.afp.afp.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
public class RockProducer {

    private final static Logger logger = LoggerFactory.getLogger(RockProducer.class);


    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;

    @Value("${rocketmq.producer.groupName}")
    private String producerGroup;

    private DefaultMQProducer producer = null;

    /**
     * 执行构造方法后调用该方法 ，建立连接
     */
    @PostConstruct
    public void initMQProducer() {
        producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setRetryTimesWhenSendFailed(3);

        try {
            producer.start();
            logger.info("rocketMQ 生产者start success！！！！！！！");
        } catch (MQClientException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 同步发送消息
     * @param topic
     * @param tags
     * @param content
     * @return
     */

    public boolean sendSync(String topic, String tags, String content) {
        // 可以将一系列业务发送至同一 topi / tag  保证该队列上的交易顺序消费
        Message msg = new Message(topic, tags, "", content.getBytes());
        // 延迟消费
        msg.setDelayTimeLevel(1);
        try {
            SendResult sendResult = producer.send(msg);
            // 单项发送消息，不关注消息成功与否 producer.sendOneway(msg);
            logger.info("rocketMQ 生产者同步推送消息成功~" + "sendResult  ；" + sendResult);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 异步发送消息
     * @param topic
     * @param tags
     * @param content
     * @return
     */
    public boolean sendAsync(String topic, String tags, String content){
        Message msg = new Message(topic, tags, "", content.getBytes());
        try {
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    logger.info("rocketMQ 生产者异步推送消息成功~" + "sendResult  ；" + sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    logger.error("rocketMQ 生产者异步推送消息失败~" + "throwable  ；" + throwable);
                }
            });
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 发送有序信息
     * @param topic
     * @param tag
     * @param content
     * @param key
     * @return
     * @throws InterruptedException
     * @throws RemotingException
     * @throws MQClientException
     * @throws MQBrokerException
     */
    public boolean sendOrder(String topic,String tag, String content, int key) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message msg = new Message(topic, tag,"", content.getBytes());

        producer.send(msg, new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                int id = (int) o ;
                logger.info(list+"");
                return list.get(id%2);
            }
        },key);
        return true;
    }

    /**
     * 批量发送消息  超过4M 需要拆分批量发送
     * @param messageList
     * @throws InterruptedException
     * @throws RemotingException
     * @throws MQClientException
     * @throws MQBrokerException
     */
    public void batchSend(List<Message> messageList) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        producer.send(messageList);

    }

    /**
     * 关闭rocket 连接
     * predestroy 注解，在进程结束前自动调用该注解，将连接关闭
     */
    @PreDestroy
    public void shutDownProducer() {
        if(producer != null) {
            producer.shutdown();
            logger.info("rocketMQ 生产者关闭长连接~ end " );
        }
    }

}
