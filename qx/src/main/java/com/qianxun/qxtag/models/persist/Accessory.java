package com.qianxun.qxtag.models.persist;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by yshaoxp on 2017/1/18.
 */
@Entity
@Table(name = "accessory")
public class Accessory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = 1;

    /* 0: 所有类别
     * 1: 打印件
     * 2: 挂件
     * 3: 贴件
     */
    @Column(nullable = false)
    private byte type = 0;

    /* 0: 所有材质
     * 1: 金属
     * 2: 塑料
     * 3: 纸质
     * 4: 木材
     */
    @Column(nullable = false)
    private byte material = 0;

    @Column(length = 40, nullable = false)
    private String name = "";

    @Column(length = 1024, nullable = false)
    private String description = "";

    @Column(length = 40, nullable = false)
    private String image = "";

    @Column(nullable = false)
    private byte discount = 10;

    @Column(nullable = false)
    private int price = 0;

    @Column(nullable = false)
    private int instock = 0;

    @Column(nullable = false)
    private byte height = 0;

    @Column(nullable = false)
    private byte width = 0;

    public Accessory(){}

    public Accessory(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte getDiscount() {
        return discount;
    }

    public void setDiscount(byte discount) {
        this.discount = discount;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getInstock() {
        return instock;
    }

    public void setInstock(int instock) {
        this.instock = instock;
    }

    public byte getHeight() {
        return height;
    }

    public void setHeight(byte height) {
        this.height = height;
    }

    public byte getWidth() {
        return width;
    }

    public void setWidth(byte width) {
        this.width = width;
    }

    public byte getMaterial() {
        return material;
    }

    public void setMaterial(byte material) {
        this.material = material;
    }
}
