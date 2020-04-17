package com.qianxun.qxtag.controllers.item;

import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import org.hibernate.Session;

import java.util.Map;

/**
 * Created by eyingsh on 10/5/2017.
 */
public class ItemStatusChangeHandler extends BaseHandler{

    long itemid;
    byte status = 0;

    public ItemStatusChangeHandler(Map<String, Object> params) {
        this(params, (byte)0);
    }

    public ItemStatusChangeHandler(Map<String, Object> params, byte status) {
        super(params);
        this.status = status;
        enableTransaction = true;
    }

    @Override
    protected void doValidate() throws Exception {
        itemid = Long.parseLong(params.get("id").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        String hql="update Item set status=:status where id=:id";

        int rows = session.createQuery(hql)
            .setParameter("status", status)
            .setParameter("id", itemid)
            .executeUpdate();
        if(rows != 1){
            throw new QianxunException(ResultCode.DatabaseCommonError,
                getClass().getName() + ":itemid=" + itemid + ",status=" + status);
        }

    }
}
