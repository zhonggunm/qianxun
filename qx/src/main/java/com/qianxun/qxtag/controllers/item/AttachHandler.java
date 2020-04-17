package com.qianxun.qxtag.controllers.item;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.controllers.ResultCode;
import org.hibernate.Session;

import java.util.Map;

/**
 * Created by yshaoxp on 2016/12/29.
 */
public class AttachHandler extends BaseHandler {

    long id;
    String image;

    public AttachHandler(Map<String, Object> params) {
        super(params);
    }

    @Override
    protected void doValidate() throws Exception {
        id = Long.parseLong(params.get("id").toString());
        image = params.get("image").toString();
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{
        String hql = "update Item t set t.image=:image where t.id=:id";

        int rows = session.createQuery(hql)
            .setParameter("image", image)
            .setParameter("id", id)
            .executeUpdate();
        if (rows != 1) {
            throw new QianxunException(ResultCode.DatabaseUpdateError, getClass().getName());
        }

    }
}
