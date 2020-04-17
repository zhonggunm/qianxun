package com.qianxun.qxtag.models.persist;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="user")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = 1;

    @Size(max = 40)
    @Column(length = 40, nullable = false)
    private String nickname = "";

    @Size(max = 1024)
    @Column(length = 1024, nullable = false)
    private String headimgurl = "";

    @Size(max = 40)
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

    @Size(max = 64)
    @Column(length = 64, nullable = false)
    private String wxunionid = "";

    @Size(max = 255)
    @Column(length = 255, nullable = false)
    private String address = "";

    @Column(nullable = false)
    private byte sex = 0;

    @Column(nullable = false)
    private short vocation = 0;

    @Column(nullable = false)
    private long credit = 0;

    @Column(nullable = false)
    private byte status = 0;

    @Column(nullable = false)
    private int balance = 0;

    @Column(nullable = false)
    private long claimed = 0;

    @Column(nullable = false)
    private long returned = 0;

    @Size(max = 40)
    @Column(length = 40, nullable = false)
    private String alipay = "";

    @Size(max = 40)
    @Column(length = 40, nullable = false)
    private String weixinpay = "";

    @Column(nullable = false)
    private int interested = 0;

    @Column(nullable = false)
    private Date birth = new Date();

    @Column(nullable = false)
    private Date creation = new Date();

    @Column(nullable = false)
    private Date lastaccess= new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area")
    private Area area;

    /*-------------------------------------------------------------------------------------------|-|*/
    /*                                                                                           |0|*/
    /*-------------------------------------------------------------------------------------------|-|*/
    @Column(nullable = false)
    private int attr1 = 0;

    @Column(nullable = false)
    private int attr2 = 0;

    public User() {}

    public User(long id) {
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

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
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

    public short getVocation() {
        return vocation;
    }

    public void setVocation(short vocation) {
        this.vocation = vocation;
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public long getClaimed() {
        return claimed;
    }

    public void setClaimed(long claimed) {
        this.claimed = claimed;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getWeixinpay() {
        return weixinpay;
    }

    public void setWeixinpay(String weixinpay) {
        this.weixinpay = weixinpay;
    }

    public int getInterested() {
        return interested;
    }

    public void setInterested(int interested) {
        this.interested = interested;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
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

    public String getWxunionid() {
        return wxunionid;
    }

    public void setWxunionid(String wxunionid) {
        this.wxunionid = wxunionid;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public long getReturned() {
        return returned;
    }

    public void setReturned(long returned) {
        this.returned = returned;
    }

    public int getAttr1() {
        return attr1;
    }

    public void setAttr1(int attr1) {
        this.attr1 = attr1;
    }

    public int getAttr2() {
        return attr2;
    }

    public void setAttr2(int attr2) {
        this.attr2 = attr2;
    }
} // class User
