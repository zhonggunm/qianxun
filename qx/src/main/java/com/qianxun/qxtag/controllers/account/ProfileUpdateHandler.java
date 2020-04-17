package com.qianxun.qxtag.controllers.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.models.persist.Admin;
import com.qianxun.qxtag.models.persist.User;
import org.hibernate.Session;

import java.util.Map;

/**
 * Created by eyingsh on 12/26/2016.
 */
public class ProfileUpdateHandler extends BaseHandler {

    User user;
    Admin admin;

    public ProfileUpdateHandler(Map<String, Object> params, Class clazz){
        super(params, clazz);
        enableTransaction = true;
    }

    @Override
    protected void prepare(Session session, Response response) throws QianxunException {
        if(clazz == User.class){
            user = new ObjectMapper().convertValue(params.get("user"), User.class);
        } else {
            admin = new ObjectMapper().convertValue(params.get("admin"), Admin.class);
        }
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{
        if(clazz == User.class){
            session.saveOrUpdate(user);
        } else {
            session.saveOrUpdate(admin);
        }
    }

}
