package com.qianxun.qxtag.controllers.item;

import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.persist.Item;
import com.qianxun.qxtag.utils.QCloudSMSUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by eyingsh on 12/21/2016.
 */
public class QueryByIdHandler extends BaseHandler {

    long id, uid;
    Item item;

    public QueryByIdHandler(Map<String, Object> params){
        super(params);
    }

    @Override
    protected void doValidate() throws Exception {
        uid = Long.parseLong(params.get("accountid").toString());
        id = Long.parseLong(params.get("id").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{
        List<Serializable> list = session.createCriteria(Item.class)
            .add(Restrictions.eq("id", id))
            .list();
        if(list.size() != 1){
            throw new QianxunException(ResultCode.QianxunItemNotFound, getClass().getName());
        } else {
            item = (Item)list.get(0);
        }
    }

    @Override
    protected void finish(Session session, Response response) throws QianxunException {
        super.finish(session, response);
        if (item.getOwner().getId() == uid){
            response.setData(item);
        } else {
            byte status = item.getStatus();
            switch (status) {
                case 2:
                    QCloudSMSUtil.sendSms(QCloudSMSUtil.QCLOUD_ALARM_TEMPLETE,
                        item.getOwner().getMobile(), item.getName());
                    throw new QianxunException(ResultCode.QianxunItemStatusError, item.getId() + ":" + status);
                case 3:
                    response.setData(item);
                    break;
                case 4: /* No break */
                case 5: /* No break */
                case 6: /* No break */
                case 7: /* No break */
                    if (item.getFounder().getId() == uid){
                        response.setData(item);
                    } else {
                        throw new QianxunException(ResultCode.QianxunItemStatusError, item.getId() + ":" + status);
                    }
                    break;
                default:
                    throw new QianxunException(ResultCode.QianxunItemStatusError, item.getId() + ":" + status);
            }
        }
    }
}
