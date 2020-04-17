package com.qianxun.qxtag.controllers.claim;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.models.persist.Claim;
import org.hibernate.Session;

import java.util.Map;

/**
 * Created by eyingsh on 12/31/2016.
 */
public class CreateClaimHandler extends BaseHandler {

    private long uid;
    private int amount;

    public CreateClaimHandler(Map<String, Object> params){
        super(params);
        enableTransaction = true;
    }

    @Override
    protected void doValidate() throws Exception {
        uid = Long.parseLong(params.get("uid").toString());
        amount = Integer.parseInt(params.get("amount").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        String hql = "update Claim t set t.user.id=:uid,t.amount=:amount,t.status=:status where t.id=:cid";

        long cid = (long)session.save(new Claim());
        int rows = session.createQuery(hql)
            .setParameter("uid", uid)
            .setParameter("amount", amount)
            .setParameter("status", 1)
            .setParameter("cid", cid)
            .executeUpdate();
        if (rows != 1) {
            throw new QianxunException(ResultCode.DatabaseUpdateError, getClass().getName());
        }


    }
}
