package com.qianxun.qxtag.controllers.item;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import org.hibernate.Session;

import java.util.Map;

/**
 * Created by yshaoxp on 2016/12/29.
 */
public class PrepayHandler extends ItemStatusChangeHandler {

    long rid;

    public PrepayHandler(Map<String, Object> params) {
        super(params, (byte)5);
    }

    @Override
    protected void doValidate() throws Exception {
        super.doValidate();
        rid = Long.parseLong(params.get("rid").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException {

        String hql = "update Reward set status='2' where id=:rid";
        int rows = session.createQuery(hql.toString())
            .setParameter("rid", rid)
            .executeUpdate();
        if (rows != 1) {
            throw new QianxunException(ResultCode.DatabaseUpdateError, getClass().getName());
        }

        hql = new String("update Item set status=:status where id=:itemid");
        rows = session.createQuery(hql.toString())
            .setParameter("status", status)
            .setParameter("itemid", itemid)
            .executeUpdate();
        if (rows != 1) {
            throw new QianxunException(ResultCode.DatabaseUpdateError, getClass().getName());
        }

    }
}
