package com.example.nb;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableDubboConfiguration
@SpringBootApplication
public class NbApplication {

    public static void main(String[] args) {
        SpringApplication.run(NbApplication.class, args);
    }

}
