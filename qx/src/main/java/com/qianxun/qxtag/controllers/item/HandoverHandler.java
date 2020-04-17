package com.qianxun.qxtag.controllers.item;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import org.hibernate.Session;

import java.util.Map;

/**
 * Created by yshaoxp on 2016/12/29.
 */
public class HandoverHandler extends ItemStatusChangeHandler {

    String agent, code;

    public HandoverHandler(Map<String, Object> params) {
        super(params, (byte)6);
    }

    @Override
    protected void doValidate() throws Exception {
        super.doValidate();
        agent = params.get("agent").toString();
        code = params.get("code").toString();
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException {

        String hql = "update Item set status=:status,express=:express,code=:code " +
            "where id=:itemid";

        int rows = session.createQuery(hql)
            .setParameter("status", status)
            .setParameter("express", agent)
            .setParameter("code", code)
            .setParameter("itemid", itemid)
            .executeUpdate();
        if (rows != 1) {
            throw new QianxunException(ResultCode.DatabaseUpdateError, getClass().getName());
        }

    }
}
