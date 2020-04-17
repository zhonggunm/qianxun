package com.qianxun.qxtag.controllers.account;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.persist.Admin;
import com.qianxun.qxtag.models.persist.ICode;
import com.qianxun.qxtag.models.persist.User;
import com.qianxun.qxtag.controllers.BaseHandler;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by yshaoxp on 2016/12/29.
 */
public class RegisterHandler extends BaseHandler {

    private String mobile, password, icode;

    public RegisterHandler(Map<String, Object> params, Class clazz) {
        super(params, clazz);
        enableTransaction = true;
    }

    @Override
    protected void doValidate() throws Exception {
        mobile = params.get("mobile").toString();
        password = params.get("password").toString();
        icode = params.get("icode").toString();
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        Criteria c = session.createCriteria(clazz)
            .add(Restrictions.eq("mobile", mobile))
            .setProjection(Projections.rowCount());
        if (Integer.parseInt(c.uniqueResult().toString()) > 0 ) {
            throw new QianxunException(ResultCode.DatabaseDuplicatedRecord, mobile);
        }

        ICode code = (ICode) session.createCriteria(ICode.class)
            .add(Restrictions.eq("mobile", mobile))
            .uniqueResult();
        if(code == null || code.getCode().compareTo(icode)!=0){
            throw new QianxunException(ResultCode.UserVerificationCodeMismatch, mobile);
        } else {
            session.delete(code);
        }

        if (clazz == User.class) {
            User acc = new User();
            acc.setMobile(mobile);
            acc.setPassword(password);
            session.save(acc);
            response.setData(acc);
        } else {
            Admin acc = new Admin();
            acc.setMobile(mobile);
            acc.setPassword(password);
            session.save(acc);
            response.setData(acc);
        }

    }

}
