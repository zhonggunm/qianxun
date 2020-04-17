package com.qianxun.qxtag.models.persist;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="admin")
public class Admin implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = 1;

    @Size(max = 40)
    @Column(length = 40, nullable = false)
    private String nickname = "";

    @Size(min = 2, max = 40)
    @Column(length = 40, nullable = false)
    private String password = "";

    @Size(max = 40)
    @Column(length = 40, nullable = false)
    private String mail = "";

    @Size(max = 40)
    @Column(length = 40, nullable = false)
    private String phone = "";

    @Size(max = 20)
    @Column(length = 20, nullable = false)
    private String mobile = "";

    @Size(max = 40)
    @Column(length = 40, nullable = false)
    private String qq = "";

    @Size(max = 40)
    @Column(length = 40, nullable = false)
    private String wechat = "";

    @Size(max = 255)
    @Column(length = 255, nullable = false)
    private String address = "";

    @Column(nullable = false)
    private byte sex = 0;

    @Column(nullable = false)
    private byte status = 0;

    @Column(nullable = false)
    private byte role = 0;

    @Column(nullable = false)
    private Date creation = new Date();

    @Column(nullable = false)
    private Date lastaccess= new Date();

    public Admin() {}

    public Admin(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getLastaccess() {
        return lastaccess;
    }

    public void setLastaccess(Date lastaccess) {
        this.lastaccess = lastaccess;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }

} // class Admin
