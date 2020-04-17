package com.qianxun.qxtag.controllers;

import com.qianxun.qxtag.models.persist.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.Map;

/**
 * Created by eyingsh on 12/30/2016.
 */
public class BaseHandler {

    protected final static int PAGE_SIZE = 64;

    protected Class clazz = User.class;
    protected boolean enableTransaction = false;
    private boolean needAuthentication = false;

    protected Map<String, Object> params;

    public BaseHandler() {}

    public BaseHandler(Map<String, Object> params){
        this(params, User.class);
    }

    public BaseHandler(Map<String, Object> params, Class clazz) {
        this.params = params;
        this.clazz = clazz;
    }

    boolean isTransactionEnabled() {
        return enableTransaction;
    }

    void authonticate(Session session, Response response) throws QianxunException {

        if (needAuthentication) {
            try{
                Criteria c = session.createCriteria(clazz)
                    .add(Restrictions.eq("accountid", Long.parseLong(params.get("accountid").toString())))
                    .add(Restrictions.eq("password", params.get("password").toString()))
                    .setProjection(Projections.rowCount());
                if (Integer.parseInt(c.uniqueResult().toString()) != 1) {
                    throw new QianxunException(ResultCode.UserAuthorntication, "User not found or non-unique");
                }
            }catch (Exception e){
                throw new QianxunException(ResultCode.UserAuthorntication, e.getCause().toString());
            }
        }
    }

    protected void validate(Session session, Response response) throws QianxunException{
        try{
            doValidate();
        }catch (Exception e){
            throw new QianxunException(ResultCode.CommonInvalidParameter,
                "[" + getClass().getName() + "]" + e.getCause().toString());
        }
    }

    protected void prepare(Session session, Response response) throws QianxunException{}
    protected void exec(Session session, Response response) throws QianxunException {}
    protected void finish(Session session, Response response) throws QianxunException{}

    protected void doValidate() throws Exception{}

    protected void logError(String msg){
        System.out.println("Qxtag:" + msg);
    }

}
