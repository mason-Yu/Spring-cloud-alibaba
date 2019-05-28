package com.nacos.alibaba.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class UmsAccount implements Serializable {
    private Integer id;
    @ApiModelProperty("账号")
    private String userName;
    @ApiModelProperty("密码")
    private String pwd;
    @ApiModelProperty("0:启用，1:禁用")
    private Boolean status;
    @ApiModelProperty("创建时间")
    private Date createTime;
    private static final long serialVersionUID = 1L;

    public UmsAccount() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(this.hashCode());
        sb.append(", id=").append(this.id);
        sb.append(", userName=").append(this.userName);
        sb.append(", pwd=").append(this.pwd);
        sb.append(", status=").append(this.status);
        sb.append(", createTime=").append(this.createTime);
        sb.append(", serialVersionUID=").append(1L);
        sb.append("]");
        return sb.toString();
    }
}