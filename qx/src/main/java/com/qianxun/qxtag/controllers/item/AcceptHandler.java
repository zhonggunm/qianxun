package com.qianxun.qxtag.controllers.item;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import org.hibernate.Session;

import java.util.Map;

public class AcceptHandler extends ItemStatusChangeHandler {

    short reward;
    long founderid;

    public AcceptHandler(Map<String, Object> params) {
        super(params, (byte)2);
    }

    @Override
    protected void doValidate() throws Exception {
        super.doValidate();
        reward = Short.parseShort(params.get("reward").toString());
        founderid = Long.parseLong(params.get("founderid").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException {

        String hql = "update Item set status=:status,reward=0,founder=null where id=:itemid";
        int rows = session.createQuery(hql.toString())
            .setParameter("status", status)
            .setParameter("itemid", itemid)
            .executeUpdate();
        if (rows != 1){
            throw new QianxunException(ResultCode.DatabaseUpdateError, getClass().getName());
        }
        if(reward > 0){
            hql = "update User t set t.balance=t.balance+" + reward + " where t.id=:userid";
            rows = session.createQuery(hql.toString())
                .setParameter("userid", founderid)
                .executeUpdate();
            if (rows != 1) {
                throw new QianxunException(ResultCode.DatabaseUpdateError, getClass().getName());
            }

        }
    }
}
