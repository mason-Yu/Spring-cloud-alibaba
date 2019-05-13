package com.nacos.alibaba.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/helloWorld")
@Slf4j
public class HelloWorld {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/helloWorld")
    public String helloWorld(){

        ServiceInstance si = loadBalancerClient.choose("alibaba-nacos-discovery-server");
        log.info("uri:"+si.getUri());
        String url = si.getUri() + "/hello/helloWorld";
        RestTemplate restTemplate = new RestTemplate();
        String value = restTemplate.getForObject(url,String.class);
        log.info("value:"+value);
        return value;
    }
}
