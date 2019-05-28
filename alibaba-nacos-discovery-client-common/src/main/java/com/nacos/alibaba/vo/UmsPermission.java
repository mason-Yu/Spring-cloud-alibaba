package com.nacos.alibaba.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class UmsPermission implements Serializable {
    private Long id;
    @ApiModelProperty("父级权限id")
    private Long pid;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("权限值")
    private String value;
    @ApiModelProperty("图标")
    private String icon;
    @ApiModelProperty("权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）")
    private Integer type;
    @ApiModelProperty("前端资源路径")
    private String uri;
    @ApiModelProperty("启用状态；0->禁用；1->启用")
    private Integer status;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("排序")
    private Integer sort;
    private static final long serialVersionUID = 1L;

    public UmsPermission() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return this.pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(this.hashCode());
        sb.append(", id=").append(this.id);
        sb.append(", pid=").append(this.pid);
        sb.append(", name=").append(this.name);
        sb.append(", value=").append(this.value);
        sb.append(", icon=").append(this.icon);
        sb.append(", type=").append(this.type);
        sb.append(", uri=").append(this.uri);
        sb.append(", status=").append(this.status);
        sb.append(", createTime=").append(this.createTime);
        sb.append(", sort=").append(this.sort);
        sb.append(", serialVersionUID=").append(1L);
        sb.append("]");
        return sb.toString();
    }
}
