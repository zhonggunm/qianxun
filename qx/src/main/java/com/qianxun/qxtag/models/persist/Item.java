package com.qianxun.qxtag.models.persist;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by yshaoxp on 2017/1/18.
 */

@Entity
@Table(name = "item")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = 1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner")
    User owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "founder")
    User founder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area")
    Area area;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accessory")
    Accessory accessory;

    @Column(length = 40, nullable = false)
    private String name = "";

    @Column(length = 1024, nullable = false)
    private String description = "";

    @Column(length = 100, nullable = false)
    private String tag = "";

    @Column(length = 40, nullable = false)
    private String image = "";

    @Column(nullable = false)
    private double latitude = 0;

    @Column(nullable = false)
    private double longitude = 0;

    @Column(nullable = false)
    private short reward = 0;

    /* 0: undefined
     * 1: reserved
     * 2: is
     * 3: oos
     * 4: notified
     * 5: reword-to-qx
     * 6: expressing
     * 7: accepted and reword-to-founder -> goto 1
     * 8: done
     * 9: expired
     * 10: died
     */
    @Column(nullable = false,columnDefinition = "tinyint default 1")
    private byte status = 1;

    /* 0: 所有类别
     * 1: 钥匙
     * 2: 证件
     * 3: 银行卡
     * 4: 箱包
     * 5: 手机数码
     * 6: 宠物
     * 7: 车辆
     * 8: 老人
     */
    @Column(nullable = false,columnDefinition = "tinyint default 0")
    private byte category = 0;

    @Column(length = 16, nullable = false)
    private String express = "";

    @Column(length = 40,nullable = false)
    private String code = "";

    @Column(nullable = false)
    private Date creation = new Date();

    @Column(nullable = false)
    private Date lostdate = new Date();

    @Column(nullable = false)
    private Date founddate = new Date();

    @Column(nullable = false)
    private Date deliverdate = new Date();

    @Column(nullable = false)
    private Date acceptdate = new Date();

    /*-------------------------------------------------------------------------------------------|-|*/
    /*                                                                                           |0|*/
    /*-------------------------------------------------------------------------------------------|-|*/
    /* 0: active(1), positive(0) */
    @Column(nullable = false)
    private int attr1 = 0;

    @Column(nullable = false)
    private int attr2 = 0;

    public Item() {}

    public Item(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getFounder() {
        return founder;
    }

    public void setFounder(User founder) {
        this.founder = founder;
    }

    public byte getCategory() {
        return category;
    }

    public void setCategory(byte category) {
        this.category = category;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public void setAccessory(Accessory accessory) {
        this.accessory = accessory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public short getReward() {
        return reward;
    }

    public void setReward(short reward) {
        this.reward = reward;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getLostdate() {
        return lostdate;
    }

    public void setLostdate(Date lostdate) {
        this.lostdate = lostdate;
    }

    public Date getFounddate() {
        return founddate;
    }

    public void setFounddate(Date founddate) {
        this.founddate = founddate;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDeliverdate() {
        return deliverdate;
    }

    public void setDeliverdate(Date deliverdate) {
        this.deliverdate = deliverdate;
    }

    public Date getAcceptdate() {
        return acceptdate;
    }

    public void setAcceptdate(Date acceptdate) {
        this.acceptdate = acceptdate;
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

}
