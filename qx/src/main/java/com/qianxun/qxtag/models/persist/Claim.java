package com.qianxun.qxtag.models.persist;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by yshaoxp on 2016/12/26.
 */
@Entity
@Table(name = "claim")
public class Claim implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id = 1;

    /* 0: unknown
     * 1: new
     * 2: paid
     * 3: rejected
     * 4: finished
     * 5: abnormal
     * 6: canceled
     */
    @Column(nullable = false)
    private int amount = 0;

    @Column(nullable = false)
    private byte status = 0;

    @Size(max = 255)
    @Column(length = 255, nullable = false)
    private String remark = "";

    @Column(nullable = false)
    private Date claimdate = new Date();

    @Column(nullable = false)
    private Date paydate = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private User user;

    public Claim(){}

    public Claim(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getClaimdate() {
        return claimdate;
    }

    public void setClaimdate(Date claimdate) {
        this.claimdate = claimdate;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
