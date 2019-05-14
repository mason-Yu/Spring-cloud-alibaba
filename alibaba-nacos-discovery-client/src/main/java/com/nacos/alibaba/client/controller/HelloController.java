package com.nacos.alibaba.client.controller;

import com.nacos.alibaba.utils.InstanceBlance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/helloWorld")
    public String HelloWorld(){
        log.info("hello world");
        URI uri = InstanceBlance.getServerUri(loadBalancerClient);
        log.info("uri: " + uri);
        String url = uri + "/hello/helloWorld";
        RestTemplate rt = new RestTemplate();
        rt.getForObject(url,String.class);
        return "hello world";
    }

}
