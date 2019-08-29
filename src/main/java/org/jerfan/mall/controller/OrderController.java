package org.jerfan.mall.controller;

import org.jerfan.mall.api.OrderApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;

/**
 * @author jerfan.cang
 * @date 2019/8/29  14:57
 */
@RestController
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderApi orderApi;

    /**
     * 通过 RestTemplate 方式访问
     * @param orderNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/order/{orderNo}",method = RequestMethod.GET)
    public String order(@PathVariable String orderNo){
        LOGGER.info("order param -- orderNo:{}",orderNo);
        RestTemplate template = new RestTemplate();
        String rs = "fail";
        try{
            rs = template.getForObject("http://localhost:8006/order/{orderNo}",String.class,orderNo);

            LOGGER.info("http://localhost:8006/order/{orderNo} response:{}",rs);
        }catch (Exception e){

            LOGGER.info("exception:{}",e);
        }
        return "query order by orderNo response :{"+rs+"}";
    }

    /**
     * 通过 feign 方式访问
     * @param orderNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/feign/order/{orderNo}",method = RequestMethod.GET)
    public String orderQuery(@PathVariable String orderNo){
        LOGGER.info("order param -- orderNo:{}",orderNo);
        String rs = "fail";
        try{
            rs = orderApi.queryOrderByOrderNo(orderNo);
            LOGGER.info("orderApi.queryOrderByOrderNo response:{}",rs);
        }catch (Exception e){
            LOGGER.info("exception:{}",e);
        }
        return "query order by orderNo response :{"+rs+"}";
    }
}
