package com.qianxun.qxtag.controllers.account;

import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.persist.User;
import org.hibernate.Session;

import java.util.Map;

/**
 * Created by eyingsh on 12/26/2016.
 */
public class ResetPasswordByOldHandler extends BaseHandler {

    long id;
    String oldpassword, newpassword;

    public ResetPasswordByOldHandler(Map<String, Object> params, Class clazz){
        super(params, clazz);
    }

    @Override
    protected void doValidate() throws Exception {
        id = Long.parseLong(params.get("id").toString());
        oldpassword = params.get("oldpassword").toString();
        newpassword = params.get("newpassword").toString();
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        String hql = "update " + (clazz == User.class ? "User" : "Admin") +
            " t set t.password=:newpassword " +
            "where t.id=:id and t.password=:oldpassword";

        int rows = session.createQuery(hql)
                .setParameter("id", id)
                .setParameter("newpassword", newpassword)
                .setParameter("oldpassword", oldpassword)
                .executeUpdate();

        if (rows != 1) {
            throw new QianxunException(ResultCode.DatabaseUpdateError, getClass().getName());
        }

    }

}
