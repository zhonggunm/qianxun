package com.qianxun.qxtag.controllers;

public class QianxunException extends RuntimeException {

    private ResultCode code = ResultCode.NoError;
    private String detail = "";

    public QianxunException(ResultCode code, String detail){
        this.code = code;
        this.detail = detail;
    }

    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Response toResponse(){
        return new Response(code, detail);
    }

    @Override
    public String toString() {
        return code.toString() + ":" + detail;
    }
}
