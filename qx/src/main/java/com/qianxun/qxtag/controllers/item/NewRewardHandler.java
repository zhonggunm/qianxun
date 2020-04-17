package com.qianxun.qxtag.controllers.item;

import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.persist.Reward;
import org.hibernate.Session;

import java.util.Map;

/**
 * Created by yshaoxp on 2016/12/29.
 */
public class NewRewardHandler extends BaseHandler{

    long itemid,fromid,toid;
    int amount;


    public NewRewardHandler(Map<String, Object> params) {
        super(params);
        enableTransaction = true;
    }

    @Override
    protected void doValidate() throws Exception {
        itemid = Long.parseLong(params.get("itemid").toString());
        amount = Integer.parseInt(params.get("amount").toString());
        fromid = Long.parseLong(params.get("from").toString());
        toid = Long.parseLong(params.get("to").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        Reward reward = new Reward();
        reward.setAmount(amount);
        reward.setStatus((byte)1);
        long rid = (long) session.save(reward);

        String hql = "update Reward t set t.item.id=:itemid,t.fromuser.id=:fromid," +
            "t.touser.id=:toid where t.id=:rid";

        int rows = session.createQuery(hql)
            .setParameter("itemid", itemid)
            .setParameter("rid", rid)
            .setParameter("fromid", fromid)
            .setParameter("toid", toid)
            .executeUpdate();
        if (rows != 1) {
            throw new QianxunException(ResultCode.DatabaseUpdateError, getClass().getName());
        }

        response.setData(reward);

    }

}
