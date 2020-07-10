package com.dlis.dip.service;

import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service(version = "1.0.0",interfaceClass = ProductInt.class,timeout = 500000)
public class ProductIntImpl implements ProductInt {

    private final static Logger logger = LoggerFactory.getLogger(ProductIntImpl.class);

    @Override
    public void one() {
        logger.info("duboo 生产者注册成功");
    }
}
