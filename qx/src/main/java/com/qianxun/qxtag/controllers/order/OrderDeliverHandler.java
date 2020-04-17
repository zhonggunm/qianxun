package com.qianxun.qxtag.controllers.order;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.controllers.BaseHandler;
import org.hibernate.Session;

import java.util.Map;

/**
 * Created by yshaoxp on 2016/12/29.
 */
public class OrderDeliverHandler extends BaseHandler {

    long id;
    String express, code;

    public OrderDeliverHandler(Map<String, Object> params){
        super(params);
    }

    @Override
    protected void doValidate() throws Exception {
        id = Long.parseLong(params.get("orderid").toString());
        express = params.get("express").toString();
        code = params.get("code").toString();
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        String hql = "update Order t set t.express=:express,t.code=:code where t.id=:id";

        int rows = session.createQuery(hql)
            .setParameter("express", express)
            .setParameter("code", code)
            .setParameter("id", id)
            .executeUpdate();
        if (rows != 1) {
            throw new QianxunException(ResultCode.DatabaseCommonError, getClass().getName());
        }

    }
}
