package com.qianxun.qxtag.controllers.item;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.persist.Item;
import com.qianxun.qxtag.models.persist.Message;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yshaoxp on 2016/12/29.
 */
public class FoundReportHandler extends ItemStatusChangeHandler {

    long ownerid, founderid;
    double latitude, longitude;
    String message;

    public FoundReportHandler(Map<String, Object> params) {
        super(params);
    }

    @Override
    protected void doValidate() throws Exception {
        super.doValidate();
        ownerid = Long.parseLong(params.get("ownerid").toString());
        founderid = Long.parseLong(params.get("founderid").toString());
        latitude = Double.parseDouble(params.get("latitude").toString());
        longitude = Double.parseDouble(params.get("longitude").toString());
        message = params.get("message").toString();
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        List c = session.createCriteria(Item.class)
            .add(Restrictions.eq("id", itemid))
            .setProjection(Projections.projectionList()
                .add(Projections.property("reward"))).list();

        String hql="update Item set status=:status,"
            + "founder.id=:founderid,latitude=:latitude,longitude=:longitude,"
            + "founddate=:founddate where id=:id";

        int rows = session.createQuery(hql.toString())
            .setParameter("status", (byte) (((short)(c.get(0)) > 0) ? 4 : 5))
            .setParameter("founderid", founderid)
            .setParameter("latitude", latitude)
            .setParameter("longitude", longitude)
            .setParameter("id", itemid)
            .setParameter("founddate", new Date())
            .executeUpdate();
        if (rows != 1) {
            throw new QianxunException(ResultCode.DatabaseUpdateError, getClass().getName());
        }

        if(!message.isEmpty()){

            hql = "update Message set fromuser.id=:fromid,touser.id=:toid," +
                "item.id=:itemid,content=:content where id=:id";
            long msgid = (long) session.save(new Message());
            rows = session.createQuery(hql)
                .setParameter("fromid", founderid)
                .setParameter("toid", ownerid)
                .setParameter("content", message)
                .setParameter("itemid", itemid)
                .setParameter("id", msgid)
                .executeUpdate();
            if (rows != 1) {
                throw new QianxunException(ResultCode.DatabaseUpdateError, getClass().getName());
            }

        }

    }
}
