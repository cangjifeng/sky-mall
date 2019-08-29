package org.jerfan.mall.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = "CLOUD-ORDER") //配置的是生产者的实例名称
public interface OrderApi {

    @RequestMapping(value = "/order/{orderNo}",method= RequestMethod.GET)
    String queryOrderByOrderNo( @PathVariable(value = "orderNo") String orderNo);
}
