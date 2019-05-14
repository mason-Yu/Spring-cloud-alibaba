package com.nacos.alibaba.server.controller;

import com.nacos.alibaba.server.service.GoodService;
import com.nacos.alibaba.utils.AjaxJson;
import com.nacos.alibaba.vo.Good;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/app/{key}/goods")
@Slf4j
public class GoodController {

    @Autowired
    GoodService goodService;

    @PostMapping("/queryGoods")
    private AjaxJson queryGoods(@PathVariable String key){
        AjaxJson aj = new AjaxJson();
        log.info("获取商品信息");
        try {
            List<Good> list = goodService.getGoods();
            log.info("商品列表：" + list.size());
            aj.setSuccess(true);
            aj.put("data",list);
            aj.setErrorCode("0");
            log.info("商品列表111：" + aj.getJsonStr());
        } catch (Exception ex){
            log.error("获取商品信息失败", ex);
            aj.setSuccess(false);
            aj.setMsg("操作失败");
        }
            return aj;
    }
}
