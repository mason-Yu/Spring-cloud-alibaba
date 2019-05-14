package com.nacos.alibaba.client.controller;

import com.nacos.alibaba.utils.AjaxJson;
import com.nacos.alibaba.utils.InstanceBlance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

// 商品

@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate resttemplate;

    @PostMapping("/getGoods")
    public AjaxJson HelloWorld(){
        log.info("获取商品信息");
        URI uri = InstanceBlance.getServerUri(loadBalancerClient);
        log.info("uri: " + uri);
        String url = uri + "/app/client1/goods/queryGoods";
        AjaxJson json = resttemplate.postForObject(url, null,AjaxJson.class);
        log.info("获取商品列表： "  + json.getJsonStr());
        return json;
    }


}
