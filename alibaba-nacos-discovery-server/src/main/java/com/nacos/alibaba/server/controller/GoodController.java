package com.nacos.alibaba.server.controller;

import com.nacos.alibaba.server.service.GoodService;
import com.nacos.alibaba.utils.AjaxJson;
import com.nacos.alibaba.vo.Good;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/app/{key}/goods")
@Slf4j
public class GoodController {

    @Autowired
    GoodService goodService;

    @GetMapping("/queryGoods")
    private AjaxJson queryGoods(@PathVariable String key){
        AjaxJson aj = new AjaxJson();
        log.info("获取商品信息");
        try {
            List<Good> list = goodService.getGoods();
            LinkedHashMap<String, Object> body = new LinkedHashMap<>();
            body.put("list",list);
            aj.setSuccess(true);
            aj.setBody(body);
            aj.setErrorCode("0");
        } catch (Exception ex){
            log.error("获取商品信息失败", ex);
            aj.setSuccess(false);
            aj.setMsg("操作失败");
        }
            return aj;
    }
}
