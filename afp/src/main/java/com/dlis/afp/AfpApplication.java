package com.dlis.afp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

//springboot 主配置类
@SpringBootApplication

//@Target({ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@Inherited
//@SpringBootConfiguration   标注该类为spring boot 得配置类 == spring configuration
//@EnableAutoConfiguration   启用自动配置功能
    //    @Target({ElementType.TYPE})
    //    @Retention(RetentionPolicy.RUNTIME)
    //    @Documented
    //    @Inherited
    //    @AutoConfigurationPackage  自动配置包 @Import({AutoConfigurationImportSelector.class}) 给容器中导入一个组件：导入得类由组件指定
        //  给启动类所在包及其子包下所有的组件导入到ioc容器中
    //    @Import({AutoConfigurationImportSelector.class})  导入自动配置选择器，最终会导入诸多配置类实现自动配置
//@ComponentScan(
//        excludeFilters = {@ComponentScan.Filter(
//                type = FilterType.CUSTOM,
//                classes = {TypeExcludeFilter.class}
//        ), @ComponentScan.Filter(
//                type = FilterType.CUSTOM,
//                classes = {AutoConfigurationExcludeFilter.class}
//        )}
//)
public class AfpApplication {

    public static void main(String[] args) {
        SpringApplication.run(AfpApplication.class, args);
    }

}
