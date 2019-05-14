package com.nacos.alibaba.utils;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

import java.net.URI;

public class InstanceBlance {

    public static URI getServerUri(LoadBalancerClient loadBalancerClient){
        // 通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
        ServiceInstance serviceInstance = loadBalancerClient.choose("alibaba-nacos-discovery-server");
        return serviceInstance.getUri();
    }
}
