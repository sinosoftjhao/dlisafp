package controller;

import com.dlis.dip.service.ProductInt;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DubboController {

    @Reference(version = "1.0.0")
    private ProductInt product;


    @RequestMapping("god.do")
    String dubbo(){
        product.one();
        return "ok";
    }
}
