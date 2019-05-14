package com.nacos.alibaba.client.controller;

import com.nacos.alibaba.utils.AjaxJson;
import com.nacos.alibaba.utils.InstanceBlance;
import com.nacos.alibaba.vo.Good;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

// 商品

@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/getGoods")
    public List HelloWorld(){
        log.info("获取商品信息");
        URI uri = InstanceBlance.getServerUri(loadBalancerClient);
        log.info("uri: " + uri);
        String url = uri + "app/client1/goods/queryGoods";
        RestTemplate rt = new RestTemplate();
        AjaxJson json = rt.getForObject(url, AjaxJson.class);
        List<Good> list = null;
        if(json.isSuccess()) {
            list = new ArrayList<>();
            if(json.getBody() != null){
                list = (List<Good>) json.getBody().get("list");
            }
        }
        log.info("获取商品列表： " , list);
        return list;
    }


}
