package com.example.study.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;

public class WebserviceTowImp implements WebserviceTwo{
    @WebMethod(operationName = "authorization" // 修改方法名
    )
    @Override
    public String authorization(@WebParam(name = "userId") String userId,
    @WebParam(name = "password") String password) {
        if ("admin".equals(userId) && "123456".equals(password)) {
            return "success";
        }
        return "error";
    }
}
