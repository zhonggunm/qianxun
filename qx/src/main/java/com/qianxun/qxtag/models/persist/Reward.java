package com.qianxun.qxtag.models.persist;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by yshaoxp on 2016/12/26.
 */
@Entity
@Table(name = "reward")
public class Reward implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id = 1;

    /* 0: unknown
     * 1: new
     * 2: prepaid
     * 3: postpaid
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
    private Date prepaydate = new Date();

    @Column(nullable = false)
    private Date postpaydate = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item")
    Item item;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fromuser")
    User fromuser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "touser")
    User touser;

    public Reward(){}

    public Reward(long id) {
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

    public Date getPrepaydate() {
        return prepaydate;
    }

    public void setPrepaydate(Date prepaydate) {
        this.prepaydate = prepaydate;
    }

    public Date getPostpaydate() {
        return postpaydate;
    }

    public void setPostpaydate(Date postpaydate) {
        this.postpaydate = postpaydate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getFromuser() {
        return fromuser;
    }

    public void setFromuser(User fromuser) {
        this.fromuser = fromuser;
    }

    public User getTouser() {
        return touser;
    }

    public void setTouser(User touser) {
        this.touser = touser;
    }
}
