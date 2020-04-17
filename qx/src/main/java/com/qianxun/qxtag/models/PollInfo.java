package com.qianxun.qxtag.models;

import java.io.Serializable;

/**
 * Created by yshaoxp on 2017/4/11.
 */
public class PollInfo implements Serializable {

    boolean newItemStatusIndication;
    boolean newMessageIndication;

    public boolean getNewItemStatusIndication() {
        return newItemStatusIndication;
    }

    public void setNewItemStatusIndication(boolean newItemStatusIndication) {
        this.newItemStatusIndication = newItemStatusIndication;
    }

    public boolean isNewMessageIndication() {
        return newMessageIndication;
    }

    public void setNewMessageIndication(boolean newMessageIndication) {
        this.newMessageIndication = newMessageIndication;
    }

}
