package com.qianxun.qxtag.controllers.account;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.controllers.BaseHandler;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by eyingsh on 12/21/2016.
 */
public class QueryHandler extends BaseHandler {

    long id;

    public QueryHandler(Map<String, Object> params, Class clazz){
        super(params, clazz);
    }

    @Override
    protected void doValidate() throws Exception {
        id = Long.parseLong(params.get("id").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        Serializable record = (Serializable)session.createCriteria(clazz)
            .add(Restrictions.eq("id", id))
            .uniqueResult();

        if(record != null){
            response.setData(record);
        }else{
            throw new QianxunException(ResultCode.DatabaseNotFound, getClass().getName());
        }

    }

}
