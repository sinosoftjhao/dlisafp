package com.dlis.dip.config;

import com.dlis.dip.mode.Userd;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//标明该类使一个配置类
@Configuration
public class MybaseConfig {

    @Bean
    public Userd user1(){
        return new Userd();
    }
}
