package com.qianxun.qxtag.controllers.account;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.models.persist.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * Created by eyingsh on 12/26/2016.
 */
public class ResetAlipayHandler extends BaseHandler {

    private long uid;
    private String password, alipay;

    public ResetAlipayHandler(@RequestBody Map<String, Object> params){
        super(params);
        enableTransaction = true;
    }

    @Override
    protected void doValidate() throws Exception {
        uid = Long.parseLong(params.get("id").toString());
        password = params.get("password").toString();
        alipay = params.get("alipay").toString();
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        String hql1 = "update User t set t.alipay=:alipay " +
            "where t.id=:id and t.password=:password";

        Criteria c = session.createCriteria(User.class)
            .add(Restrictions.eq("alipay", alipay))
            .setProjection(Projections.rowCount());
        if (Integer.parseInt(c.uniqueResult().toString()) > 0) {
            throw new QianxunException(ResultCode.DatabaseDuplicatedRecord, getClass().getName() + ":" + alipay);
        } else {
            int rows = session.createQuery(hql1)
                .setParameter("id", uid)
                .setParameter("password", password)
                .setParameter("alipay", alipay)
                .executeUpdate();
            if (rows != 1){
                throw new QianxunException(ResultCode.DatabaseUpdateError, getClass().getName());
            }

        }

    }

}
