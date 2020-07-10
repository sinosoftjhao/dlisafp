package com.dlis.dip.mode;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

//加载指定配置文件成全局配置文件 注：只可以加载properties文件
@Component
@PropertySource(value = "classpath:product.properties")
@ConfigurationProperties(prefix = "product")
@Validated
@Getter
@Setter
@ToString
public class Product {

    private Integer productId;

    private String pname;

    private List<Userd> list;

    private Userd userd;

    private Map<String,Userd> map;

}
