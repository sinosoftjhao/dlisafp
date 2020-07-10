package com.example.study.service;


import org.springframework.stereotype.Component;




@Component
public class WebserviceImp implements WebserviceServer {
    @Override
    public String sayGoby(String name) {
        System.out.println("牛逼阿，兄弟  : " + name);
        return "DILS： + 你好  ：" + name;
    }
}
