package com.qianxun.qxtag.controllers.account;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.persist.ICode;
import com.qianxun.qxtag.models.persist.User;
import com.qianxun.qxtag.controllers.BaseHandler;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Map;

/**
 * Created by eyingsh on 12/26/2016.
 */
public class ResetPasswordByICodeHandler extends BaseHandler {

    String mobile, icode, newpassword;

    public ResetPasswordByICodeHandler(Map<String, Object> params, Class clazz){
        super(params, clazz);
        enableTransaction = true;
    }

    @Override
    protected void doValidate() throws Exception {
        mobile = params.get("mobile").toString();
        icode = params.get("icode").toString();
        newpassword = params.get("newpassword").toString();
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException {

        ICode code = (ICode) session.createCriteria(ICode.class)
            .add(Restrictions.eq("mobile", mobile))
            .uniqueResult();
        if (code == null || code.getCode().compareTo(icode) != 0) {
            throw new QianxunException(ResultCode.UserVerificationCodeMismatch, mobile);
        } else {
            session.delete(code);
        }

        String hql = "update " + (clazz == User.class ? "User" : "Admin")
            + " set password=:newpassword where mobile=:mobile";

        int rows = session.createQuery(hql)
                .setParameter("mobile", mobile)
                .setParameter("newpassword", newpassword)
                .executeUpdate();
        if (rows != 1){
            throw new QianxunException(ResultCode.UserNotFound, getClass().getName());
        }

    }

}
