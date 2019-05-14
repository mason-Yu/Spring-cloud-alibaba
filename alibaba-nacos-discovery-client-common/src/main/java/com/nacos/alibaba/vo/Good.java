package com.nacos.alibaba.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Good implements Serializable {

    private Integer id;
    private String goodName;
    private String proAddress; // 生产地址
    private String goodNum;
    private Date startTime;
    private Date endTime;

}
