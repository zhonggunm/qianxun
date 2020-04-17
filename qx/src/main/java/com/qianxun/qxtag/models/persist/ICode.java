package com.qianxun.qxtag.models.persist;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by eyingsh on 12/29/2016.
 */
@Entity
@Table(name="icode")
public class ICode implements Serializable {

    @Id
    @Column(unique = true, nullable = false)
    private String mobile = "";

    @Size(max = 8)
    @Column(length = 8, nullable = false)
    private String code = "";

    @Column(nullable = false)
    private Date creation = new Date();

    public ICode() {}

    public ICode(String mobile, String code) {
        this.mobile = mobile;
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }
}
