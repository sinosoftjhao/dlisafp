package com.dlis.afp.afp.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

@Component
public class JedisClient {

    private final static Logger logger = LoggerFactory.getLogger(JedisClient.class);

    @Autowired
    private JedisCluster jedisCluster;

    public String set(String key,Object value){
        logger.info("key :" + key +"；" + "value" + value);
        return jedisCluster.set(key,value.toString());
    }
}
