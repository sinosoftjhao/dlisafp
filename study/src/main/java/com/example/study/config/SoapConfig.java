
package com.example.study.config;

;
import com.example.study.service.WebserviceImp;
import com.example.study.service.WebserviceServer;
import com.example.study.service.WebserviceTowImp;
import com.example.study.service.WebserviceTwo;
import org.apache.cxf.Bus;

import javax.xml.ws.Endpoint;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SoapConfig {

    @Autowired
    private WebserviceServer webserviceServer;

    @Autowired
    private WebserviceTwo webserviceTwo;
    /**
     * 提供wsdl接口规范，没有也可以 ， 注：name 必写，否则与mvc的DISServlet 冲突
     * @return
     */
    @Bean(name = "cxfServlet")
    public ServletRegistrationBean<CXFServlet> dispatcherServlet() {
        return new ServletRegistrationBean<CXFServlet>(new CXFServlet(), "/service/*");
    }

    /**
     *
     * @return
     */
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    /**
     *
     * 服务接口
     * @return
     */
    @Bean
    public WebserviceServer webService() {
        return new WebserviceImp();
    }

    @Bean
    public WebserviceTwo webserviceTwo(){
        return new WebserviceTowImp();
    }
    /**
     * 发布服务
     * @return
     */
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), webService());
        endpoint.publish("/dlis");
        return endpoint;
    }

    @Bean
    public Endpoint endpoint2() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), webserviceTwo());
        endpoint.publish("/two");
        return endpoint;
    }
}
