package com.dlis.afp.afp.zookeeper;

import com.dlis.afp.afp.redis.JedisClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ZkClient {

    private final static Logger logger = LoggerFactory.getLogger(ZkClient.class);

    @Autowired
    private ZooKeeper zooKeeper;

    public boolean createNode(String path, String data) {
        try {
            zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            return true;
        } catch (Exception e) {
            System.err.println("【创建持久化节点异常】{},{},{}" +  path + data + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


}
