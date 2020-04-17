package com.qianxun.qxtag.models.persist;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by eyingsh on 12/29/2016.
 */
@Entity
@Table(name="area")
public class Area implements Serializable {

    @Id
    @Column(unique = true, nullable = false)
    private int id = 0;

    @Column(nullable = false)
    private int leftcode = 0;

    @Column(nullable = false)
    private int rightcode = 0;

    @Column(nullable = false)
    private int pid = 0;

    @Size(max = 40)
    @Column(length = 40, nullable = false)
    private String name = "";

    @Size(max = 40)
    @Column(length = 40, nullable = false)
    private String pinyin="";

    @Size(max = 20)
    @Column(length = 20, nullable = false)
    private String code="";

    @Size(max = 10)
    @Column(length = 10, nullable = false)
    private String zip="";

    public Area(){};

    public Area(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeftcode() {
        return leftcode;
    }

    public void setLeftcode(int leftcode) {
        this.leftcode = leftcode;
    }

    public int getRightcode() {
        return rightcode;
    }

    public void setRightcode(int rightcode) {
        this.rightcode = rightcode;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
