package com.dlis.afp.service;

import com.dlis.afp.afp.redis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    private JedisClient jedisClient ;

    public String set(String key,String value){
        return jedisClient.set(key,value);
    }
}
