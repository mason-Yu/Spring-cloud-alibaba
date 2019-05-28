package com.nacos.alibaba.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WechatMenu implements java.io.Serializable{

    private int id;
    private String eventKey; // 菜单对应的eventkey
    private String url; // view菜单对应的url或者是图片对应的url
    private String name; //菜单名字
    private String cdate; // 菜单创建时间
    private String udate; // 菜单修改时间
    private String type; // 菜单的响应动作类型
    private int status; // 菜单是否删除
    private int parentId; // 父菜单Id
    private String parentName; // 父菜单
    private String mediaId;
    private String appId; // 小程序app id
    private String pagePath; // 小程序的页面路径
}
