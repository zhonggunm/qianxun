package com.qianxun.qxtag.controllers;

import java.io.Serializable;

/**
 * Created by yshaoxp on 2016/12/29.
 */
public class Response implements Serializable {

    ResultCode code = ResultCode.NoError;
    Serializable data = "";

    public Response() {}

    public Response(ResultCode code, Serializable content) {
        this.code = code;
        this.data = content;
    }

    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }

    public Serializable getData() {
        return data;
    }

    public void setData(Serializable data) {
        this.data = data;
    }

    public void setResponse(ResultCode code, Serializable content){
        this.code = code;
        this.data = content;
    }

}
