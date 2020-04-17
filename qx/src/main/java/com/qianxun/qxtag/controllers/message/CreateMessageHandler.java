package com.qianxun.qxtag.controllers.message;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.persist.Message;
import com.qianxun.qxtag.controllers.BaseHandler;
import org.hibernate.Session;

import java.util.Map;

/**
 * Created by eyingsh on 12/31/2016.
 */
public class CreateMessageHandler extends BaseHandler {

    private long messageid,fromid,toid,itemid;
    String content;

    public CreateMessageHandler(Map<String, Object> params){
        super(params);
        enableTransaction = true;
    }

    @Override
    protected void doValidate() throws Exception {
        messageid = Long.parseLong(params.get("messageid").toString());
        fromid = Long.parseLong(params.get("fromid").toString());
        toid = Long.parseLong(params.get("toid").toString());
        itemid = Long.parseLong(params.get("itemid").toString());
        content = params.get("content").toString();
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        String hql = "update Message t set t.fromuser.id=:fromid,t.touser.id=:toid," +
            "t.item.id=:itemid,t.content=:content,t.followedby=:followedby " +
            "where t.id=:id";

        long id = (long)session.save(new Message());
        int rows = session.createQuery(hql)
            .setParameter("fromid", fromid)
            .setParameter("toid", toid)
            .setParameter("content", content)
            .setParameter("itemid", itemid)
            .setParameter("id", id)
            .setParameter("followedby", messageid)
            .executeUpdate();
        if (rows != 1) {
            throw new QianxunException(ResultCode.DatabaseUpdateError, getClass().getName());
        }

    }
}
