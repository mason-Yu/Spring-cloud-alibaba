package com.nacos.alibaba.server.service.impl;



import com.nacos.alibaba.server.mapper.GoodMapper;
import com.nacos.alibaba.server.service.GoodService;
import com.nacos.alibaba.vo.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodMapper goodMapper;

    @Override
    public List<Good> getGoods() {
       return goodMapper.queryGoods();
    }
}
