package com.dlis.dip;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.dlis.afp.afp")
@EnableDubbo
public class DipApplication {

    public static void main(String[] args) {
        SpringApplication.run(DipApplication.class, args);
    }

}
