package com.fuyi.upms.dao.entity;

import java.util.List;

public class UpmsPermission {
    private Integer permissionId;

    private Integer systemId;

    private Integer pid;

    private String name;

    private Byte type;

    private String permissionValue;

    private String uri;

    private String icon;

    private Byte status;

    private Long ctime;

    private Long orders;

    private List<UpmsRole> roles;

    private List<UpmsPermission> children;

    public List<UpmsPermission> getChildren() {
        return children;
    }

    public void setChildren(List<UpmsPermission> children) {
        this.children = children;
    }

    public List<UpmsRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UpmsRole> roles) {
        this.roles = roles;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue == null ? null : permissionValue.trim();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }
}