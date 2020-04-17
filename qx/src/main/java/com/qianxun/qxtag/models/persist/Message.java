package com.qianxun.qxtag.models.persist;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by yshaoxp on 2017/1/18.
 */
@Entity
@Table(name = "message")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = 1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fromuser")
    User fromuser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "touser")
    User touser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item")
    Item item;

    @Column(length = 1024, nullable = false)
    private String content = "";

    @Column(nullable = false)
    private Date creation = new Date();

    @Column(nullable = false)
    private long followedby = 0;

    public Message() {}

    public Message(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public long getFollowedby() {
        return followedby;
    }

    public void setFollowedby(long followedby) {
        this.followedby = followedby;
    }

}
