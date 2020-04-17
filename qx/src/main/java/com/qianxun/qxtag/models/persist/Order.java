package com.qianxun.qxtag.models.persist;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

/**
 * Created by yshaoxp on 2017/1/18.
 */
/* orders instead of order to bypass mysql keyword order */
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = 1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner")
    User owner;

    @Column(length = 40, nullable = false)
    private String content = "";

    @Column(nullable = false)
    private int amount = 0;

    @Column(nullable = false)
    private int total = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area")
    Area guestArea;

    @Size(max = 40)
    @Column(length = 40, nullable = false)
    private String guestName = "";

    @Size(max = 20)
    @Column(length = 40, nullable = false)
    private String guestMobile = "";

    @Size(max = 120)
    @Column(length = 40, nullable = false)
    private String guestAddress = "";

    @Column(length = 16, nullable = false)
    private String express = "";

    @Column(length = 40,nullable = false)
    private String code = "";

    @Column(nullable = false)
    private Date creation = new Date();

    @Column(nullable = false)
    private Date transfering = new Date();

    @Column(nullable = false)
    private Date completion = new Date();

    @Column(length = 128,nullable = false)
    private String payment = "";

    /* 0: undefined
     * 1: new
     * 2: paid
     * 3: expressing
     * 4: received
     * 5: completed
     * 6: abnormal
     * 7: canceled
     */
    @Column(nullable = false)
    private byte status = 0;

    public Order() {}

    public Order(long id) {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Area getGuestArea() {
        return guestArea;
    }

    public void setGuestArea(Area guestArea) {
        this.guestArea = guestArea;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestMobile() {
        return guestMobile;
    }

    public void setGuestMobile(String guestMobile) {
        this.guestMobile = guestMobile;
    }

    public String getGuestAddress() {
        return guestAddress;
    }

    public void setGuestAddress(String guestAddress) {
        this.guestAddress = guestAddress;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getTransfering() {
        return transfering;
    }

    public void setTransfering(Date transfering) {
        this.transfering = transfering;
    }

    public Date getCompletion() {
        return completion;
    }

    public void setCompletion(Date completion) {
        this.completion = completion;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
