package com.qianxun.qxtag.models;

import java.io.Serializable;

public class CommonMessage implements Serializable{

    String message;

    public CommonMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
