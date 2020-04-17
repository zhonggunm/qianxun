package com.qianxun.qxtag.controllers.item;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.persist.Item;
import com.qianxun.qxtag.controllers.BaseHandler;
import org.hibernate.Session;

import java.util.Map;

/**
 * Created by yshaoxp on 2017/1/19.
 */
public class UpdateHandler extends BaseHandler {

    Item item = new Item();
    String hql = null;

    public UpdateHandler(Map<String, Object> params) {
        super(params);
    }

    @Override
    protected void prepare(Session session, Response response) throws QianxunException {

        try {
            item.setId(Long.parseLong(params.get("id").toString()));
            item.setStatus(Byte.parseByte(params.get("status").toString()));

            switch (item.getStatus()) {
                case 0:
                    // TODO: error handling
                    break;
                case 1:
                    // TODO: error handling
                    break;
                case 2:
                    // TODO: error handling
                    break;
                case 3:
                    item.setReward(Short.parseShort(params.get("reword").toString()));
                    break;
                case 4:
                    // paid: no parameters
                    break;
                case 5:
                    item.getFounder().setId(Long.parseLong(params.get("founder").toString()));
                    break;
                case 6:
                    item.setExpress(params.get("agent").toString());
                    item.setCode(params.get("code").toString());
                    break;
                case 7:
                    // TODO: error handling
                    break;
                case 8:
                    // TODO: error handling
                    break;
                case 9:
                    // TODO: error handling
                    break;
                default:
                    // TODO: error handling
                    break;
            }


        } catch (Exception e) {
            throw new QianxunException(ResultCode.CommonInvalidParameter, e.getCause().toString());
        }

    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        int rows = 0;

        switch (item.getStatus()) {
            case 0:
                // TODO: error handling
                break;
            case 1:
                // TODO: error handling
                break;
            case 2:
                hql="update Item t set t.reword=:reword, t.status=:status where t.id=:id";
                rows = session.createQuery(hql)
                    .setParameter("status", item.getStatus())
                    .setParameter("reword", item.getReward())
                    .setParameter("id", item.getId())
                    .executeUpdate();
                break;
            case 3:
                hql = "update Item t set t.founder.id=:founder, t.status=:status where t.id=:id";
                rows = session.createQuery(hql)
                    .setParameter("status", item.getStatus())
                    .setParameter("founder", item.getFounder().getId())
                    .setParameter("id", item.getId())
                    .executeUpdate();
                break;
            case 4:
                // paid: no parameters
                break;
            case 5:
               break;
            case 6:
                // TODO: error handling
                break;
            case 7:
                // TODO: error handling
                break;
            case 8:
                // TODO: error handling
                break;
            default:
                // TODO: error handling
                break;
        }
    }

}
