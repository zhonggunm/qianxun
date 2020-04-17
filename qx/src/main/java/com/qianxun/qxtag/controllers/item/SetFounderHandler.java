package com.qianxun.qxtag.controllers.item;

import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import org.hibernate.Session;

import java.util.Map;

public class SetFounderHandler extends BaseHandler {

    long itemid, founderid;

    public SetFounderHandler(Map<String, Object> params) {
        super(params);
        enableTransaction = true;
    }

    @Override
    protected void doValidate() throws Exception {
        itemid = Long.parseLong(params.get("id").toString());
        founderid = Long.parseLong(params.get("founder").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException {
        String hql = "update Item set founder.id=:founderid where id=:itemid";
        int rows = session.createQuery(hql.toString())
            .setParameter("itemid", itemid)
            .setParameter("founderid", founderid)
            .executeUpdate();
        if (rows != 1){
            throw new QianxunException(ResultCode.DatabaseUpdateError, getClass().getName());
        }
    }
}
