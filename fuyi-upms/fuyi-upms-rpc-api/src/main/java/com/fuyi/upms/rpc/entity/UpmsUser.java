package com.fuyi.upms.rpc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

public class UpmsUser implements Serializable {

    public UpmsUser() {}

    public UpmsUser(Integer userId, String username, String password, String salt,
                    String realname, String avatar, String phone,
                    String email, Byte sex, Byte locked, Long ctime,
                    List<UpmsRole> roles) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.realname = realname;
        this.avatar = avatar;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.locked = locked;
        this.ctime = ctime;
        this.roles = roles;
    }

    private Integer userId;

    private String username;

    @JsonIgnore
    private String password;

    private String salt;

    private String realname;

    private String avatar;

    private String phone;

    private String email;

    private Byte sex;

    private Byte locked;

    private Long ctime;

    private List<UpmsRole> roles;

    public List<UpmsRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UpmsRole> roles) {
        this.roles = roles;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Byte getLocked() {
        return locked;
    }

    public void setLocked(Byte locked) {
        this.locked = locked;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }
}