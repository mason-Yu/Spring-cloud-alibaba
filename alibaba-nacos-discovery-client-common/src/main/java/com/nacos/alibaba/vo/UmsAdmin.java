package com.nacos.alibaba.vo;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class UmsAdmin implements Serializable {
    private Long id;
    private String username;
    private String password;
    @ApiModelProperty("头像")
    private String icon;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("备注信息")
    private String note;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("最后登录时间")
    private Date loginTime;
    @ApiModelProperty("帐号启用状态：0->禁用；1->启用")
    private Integer status;
    private static final long serialVersionUID = 1L;

    public UmsAdmin() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(this.hashCode());
        sb.append(", id=").append(this.id);
        sb.append(", username=").append(this.username);
        sb.append(", password=").append(this.password);
        sb.append(", icon=").append(this.icon);
        sb.append(", email=").append(this.email);
        sb.append(", nickName=").append(this.nickName);
        sb.append(", note=").append(this.note);
        sb.append(", createTime=").append(this.createTime);
        sb.append(", loginTime=").append(this.loginTime);
        sb.append(", status=").append(this.status);
        sb.append(", serialVersionUID=").append(1L);
        sb.append("]");
        return sb.toString();
    }
}

