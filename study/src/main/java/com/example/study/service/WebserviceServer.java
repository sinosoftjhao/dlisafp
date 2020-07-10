package com.example.study.service;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(
        serviceName = "WebserviceServer", // 修改WebService服务名称
        targetNamespace = "http://service.study.example.com" // 定义命名空间，默认为倒置的包名
)
public interface WebserviceServer {
    @WebMethod
    public String sayGoby(@WebParam(name = "name") String name);
}
