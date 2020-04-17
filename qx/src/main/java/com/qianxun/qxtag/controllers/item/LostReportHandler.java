package com.qianxun.qxtag.controllers.item;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.utils.QCloudSMSUtil;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yshaoxp on 2016/12/29.
 */
public class LostReportHandler extends ItemStatusChangeHandler {

    short reward;
    Object[] obj = null;

    public LostReportHandler(Map<String, Object> params) {
        super(params, (byte)3);
    }

    @Override
    protected void doValidate() throws Exception {
        super.doValidate();
        reward = Short.parseShort(params.get("reward").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException {

        String hql = "update Item set status=:status,reward=:reward,lostdate=:lostdate "
            + "where id=:id";
        int rows = session.createQuery(hql.toString())
            .setParameter("status", status)
            .setParameter("reward", reward)
            .setParameter("lostdate", new Date())
            .setParameter("id", itemid)
            .executeUpdate();
        if (rows != 1) {
            throw new QianxunException(ResultCode.DatabaseUpdateError, getClass().getName());
        }

        hql = "select t1.name,t2.mobile from Item as t1,User as t2 " +
            "where t1.founder.id=t2.id and t1.id=:id ";
        List<Object> r = session.createQuery(hql).setParameter("id", itemid).list();
        if (r.size() == 1){
            obj = (Object[]) r.get(0);
        } else {
            logError(getClass().getName() + ":size=" + r.size());
        }
    }

    @Override
    protected void finish(Session session, Response response) throws QianxunException {
        super.finish(session, response);
        if (obj != null){
            String name = (String) obj[0];
            String mobile = (String) obj[1];
            if (!mobile.isEmpty()) {
                QCloudSMSUtil.sendSms(QCloudSMSUtil.QCLOUD_NOTIFY_TEMPLETE, mobile, name);
            }
        }
    }
}
