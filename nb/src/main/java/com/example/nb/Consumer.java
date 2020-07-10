package com.example.nb;

import com.dlis.dip.service.ProductInt;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @Reference(version = "1.0.0")
    private ProductInt productInt;

    void Consumer(){
        productInt.one();
    }

}
