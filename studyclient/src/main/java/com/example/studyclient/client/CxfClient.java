package com.example.studyclient.client;

import com.example.study.service.WebserviceImp;
import com.example.study.service.WebserviceServer;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.jaxws.spring.JaxWsProxyFactoryBeanDefinitionParser;
import org.apache.cxf.jaxws.spring.JaxWsProxyFactoryBeanDefinitionParser.JAXWSSpringClientProxyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;
import java.net.URL;

@Component
public class CxfClient {

    private final static Logger logger = LoggerFactory.getLogger(CxfClient.class);


    @Value("${soapserver}")
    private String methodUrl;

    @Value("#{'http://service.study.example.com'}")
    private String namespaceURI;


    /**
     * spring + cxf
     *  方式 1 : axis 调用 目前中台用到的调用保单打印平台的方式 不依赖Jar包
     * @param arg0
     * @return
     * @throws Exception
     */
    public Object axisClient(String arg0) throws Exception {
        logger.info("webservice axis调用开始" );
        Service service = new Service();
        Call call = (Call) service.createCall();
        logger.info("调用参数  : url = " + methodUrl + "  namespaceURI = " + namespaceURI );
        call.setTargetEndpointAddress(new URL(methodUrl));
        // 服务方接口的包路径反坠 + 服务端方法名
        call.setOperationName(new QName("http://service.study.example.com","sayGoby"));
        // 参数名，与服务端方法参数一致  参数类型 入参
        call.addParameter("name", XMLType.XSD_STRING , ParameterMode.IN);
        // 返回值参数类型
        call.setReturnType(XMLType.XSD_STRING);
        // 超时时间
        call.setTimeout(30000);
        // 开始调用
        return call.invoke(new Object[]{arg0});
    }

    /**
     * spring boot + cxf 需要引入服务端代码（用的比较少）
     * 方法二 ： 通过JAXWSSpringClientProxyFactoryBean Spring代理工厂类 参数为url+wsdl 生成代理对象实现调用
     * @param arg0
     * @return
     */
    public Object jarClient(String arg0){
        logger.info("webservice spring boot + cxf 需要引入服务端代码 调用开始" );
        JAXWSSpringClientProxyFactoryBean jaxwsSpringClientProxyFactoryBean = new JAXWSSpringClientProxyFactoryBean();
        logger.info("调用参数  : url = " + methodUrl );
        jaxwsSpringClientProxyFactoryBean.setAddress(methodUrl);
        jaxwsSpringClientProxyFactoryBean.setServiceClass(WebserviceServer.class);
        WebserviceServer webserviceProxy = (WebserviceServer) jaxwsSpringClientProxyFactoryBean.create();
        return webserviceProxy.sayGoby(arg0);
    }

    /**
     * spring boot + cxf 动态代理，不依赖服务端代码
     * @param arg0
     * @return
     * @throws Exception
     */
    public Object cxfDyClient(String arg0) throws Exception {
        logger.info("webservice spring boot + cxf 动态代理，不依赖服务端代码 调用开始" );
        JaxWsDynamicClientFactory jaxWsDynamicClientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = jaxWsDynamicClientFactory.createClient("http://192.168.43.251:9000/service/two/?wsdl");
        QName qName = new QName("http://com.example.study.service/my","authorization");
         Object[] result = client.invoke(qName,
                new Object[] { "admin", "123456" });
         return null;
    }


    /**
     * spring boot + cxf 动态代理，不依赖服务端代码
     * @param arg0
     * @return
     * @throws Exception
     */
    public Object cxfDyClient2(String arg0) throws Exception {
        logger.info("webservice spring boot + cxf 动态代理，不依赖服务端代码 调用开始" );
        JaxWsDynamicClientFactory jaxWsDynamicClientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = jaxWsDynamicClientFactory.createClient("http://2.0.1.14:9000/service/dlis/?wsdl");
        QName qName = new QName("http://service.study.example.com","sayGoby");
        Object[] result = client.invoke(qName,
                arg0);
        return null;
    }
}
