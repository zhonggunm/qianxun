package com.qianxun.qxtag.controllers.common;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.PollInfo;
import com.qianxun.qxtag.models.persist.Item;
import com.qianxun.qxtag.models.persist.Message;
import com.qianxun.qxtag.controllers.BaseHandler;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by eyingsh on 12/31/2016.
 */
public class QueryPollInfoHandler extends BaseHandler {

    long uid;
    Date time;
    public QueryPollInfoHandler(Map<String, Object> params){
        super(params);
    }

    @Override
    protected void doValidate() throws Exception {
        uid = Long.parseLong(params.get("uid").toString());
        time = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").parse(params.get("time").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        PollInfo info = new PollInfo();

        Criteria c = session.createCriteria(Item.class)
            .add(Restrictions.eq("owner.id", uid))
            .add(Restrictions.eq("status", (byte)4))
            .add(Restrictions.gt("founddate", time))
            .setProjection(Projections.rowCount());;
        info.setNewItemStatusIndication(Integer.parseInt(c.uniqueResult().toString()) > 0);

        c = session.createCriteria(Message.class)
            .add(Restrictions.eq("touser.id", uid))
            .add(Restrictions.gt("creation", time))
            .setProjection(Projections.rowCount());
        info.setNewMessageIndication(Integer.parseInt(c.uniqueResult().toString()) > 0);

        response.setData(info);
    }
}
