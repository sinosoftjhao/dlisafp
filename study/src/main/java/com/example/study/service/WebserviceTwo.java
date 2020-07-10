package com.example.study.service;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "Login",// 定义Port名称
        serviceName = "WebserviceTwo", // 修改WebService服务名称
        targetNamespace = "http://com.example.study.service/my" // 定义命名空间，默认为倒置的包名
)
public interface WebserviceTwo {
    @WebMethod(operationName = "authorization")
    String authorization(@WebParam(name = "userId") String userId,
                         @WebParam(name = "password") String password);
}
