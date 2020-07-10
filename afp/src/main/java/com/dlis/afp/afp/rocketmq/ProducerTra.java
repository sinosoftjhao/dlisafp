package com.dlis.afp.afp.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * 流程
 * producer 发送消息到 broker
 * broker 回调监听器方法 exectuteLocalTracnsaction 查看本地事务状态 （三种状态 1、commit 2、rollback 3、未知）
 * 若状态为 commit  则消费者可以进行消费  若为 未知调用检查  若为 rollback 则删除消息
 */
@Service
public class ProducerTra {

    private TransactionMQProducer transactionMQProducer;
    @Value("${rocketmq.producer.namesrvAddr}")
    private String nameServer;

    /**
     * 初始化事务生产者
     * 1、创建TransactionMQProducet
     * 2、指定nameServer
     * 3、指定监听回调器 （ 两个方法实现 ）
     */
    @PostConstruct
    void init() throws MQClientException {
        transactionMQProducer = new TransactionMQProducer("transationProducer");
        transactionMQProducer.setNamesrvAddr(nameServer);
        transactionMQProducer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                 return LocalTransactionState.COMMIT_MESSAGE;

            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                return null;
            }
        });
        transactionMQProducer.start();
    }

    void send(Message message) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        transactionMQProducer.send(message);
    }

    @PreDestroy
    void shutDown(){
        transactionMQProducer.shutdown();
    }

}
