package com.qianxun.qxtag.controllers.account;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.controllers.ResultCode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by yshaoxp on 2016/12/29.
 */
public class LoginHandler extends BaseHandler {

    private final static String MOBILE_PATTERN = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    String mobile, password;

    public LoginHandler(Map<String, Object> params, Class clazz) {
        super(params, clazz);
    }

    @Override
    protected void doValidate() throws Exception {
        mobile = params.get("mobile").toString();
        password = params.get("password").toString();
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        List<Serializable> list = session.createCriteria(clazz)
            .add(Restrictions.eq("mobile", mobile))
            .add(Restrictions.eq("password", password))
            .list();
        if (list.size() != 1){
            throw new QianxunException(ResultCode.UserNotFound, mobile);
        }else{
            response.setData(list.get(0));
        }

    }

}
