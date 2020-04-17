package com.qianxun.qxtag.models.persist;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by yshaoxp on 2017/1/18.
 */
@Entity
@Table(name = "orderitem")
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = 1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orders")
    Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accessory")
    Accessory accessory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item")
    Item item;

    @Column(nullable = false)
    private int amount = 0;

    @Column(nullable = false)
    private int price = 0;

    public OrderItem() {
    }

    public OrderItem(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public void setAccessory(Accessory accessory) {
        this.accessory = accessory;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
