package com.dlis.dip;



import com.dlis.afp.afp.jdbc.OracleClient;
import com.dlis.afp.afp.redis.JedisClient;
import com.dlis.dip.mapper.UserdMapper;
import com.dlis.dip.mode.Product;
import com.dlis.dip.mode.Userd;
import com.dlis.dip.mode.UserdExample;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
//
import java.util.HashSet;
//
@EnableAutoConfiguration
@ComponentScan (value = "com.dlis.afp.afp")
////@ComponentScans(value={
////        @ComponentScan (value = "com.dlis.afp.afp.jdbc"),
////        @ComponentScan (value = "com.dlis.afp.afp.redis"),
////        @ComponentScan (value = "com.dlis.afp.afp.jmsmq"),
////        @ComponentScan (value = "com.dlis.afp.afp.zookeeper")
////})
@Configuration
@MapperScan("com.dlis.dip.mapper")
@SpringBootTest
// 将userd.xml 加载到Spring容器中来，实现ioc注入
@ImportResource(locations = ("classpath:userd.xml"))
class DipApplicationTests {

    @Autowired
    OracleClient oracleClient;
    @Autowired
    UserdMapper userdMapper;
    @Autowired
    JedisClient jedisClient;
//    @Autowired
//    Product product;
    @Autowired
    Userd userd;
    @Autowired
    ApplicationContext ioc;
    @Test
    void contextLoads() {
        HashSet<Object> objects = new HashSet<>();
    }
    @Test
    void sqlTest(){
        oracleClient.exeSql("insert into a (aa) values ('02')");
    }
    @Test
    void mybatisTest(){
        Userd userd = new Userd();
        userd.setUsername("陶竞舸");
        userd.setPassword("123456");
        userd.setId(8l);
        userd.setRelaname("1");
        userdMapper.insert(userd);
        UserdExample example = new UserdExample();
        example.createCriteria().andPasswordEqualTo("123456");
        System.out.println(userdMapper.selectByExample(example));
    }
    @Test
    void lombokMybatis(){
        for (Userd u:userdMapper.selectByExample(null)
             ) {
            System.out.println(u);

        }
    }
    @Test
    void jedisTest(){
        jedisClient.set("jwh","aaa");
    }

//    @Test
//    void ymlTest(){
//        System.out.println(product);
//    }
    @Test
    void importResource(){
        System.out.println(ioc.containsBean("user"));
        System.out.println(userd);
    }
}
