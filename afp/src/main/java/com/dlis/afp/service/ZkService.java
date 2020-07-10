package com.dlis.afp.service;


import com.dlis.afp.afp.zookeeper.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;

public class ZkService {
    @Autowired
    private ZkClient zkClient;

    public boolean createNode(String path, String data){
        return zkClient.createNode(path,data);
    }
}
