package com.qianxun.qxtag.controllers.item;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.persist.Item;
import com.qianxun.qxtag.controllers.BaseHandler;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by eyingsh on 12/21/2016.
 */
public class QueryByFounderHandler extends BaseHandler {

    long id;
    byte status;
    private int page;

    QueryByFounderHandler(Map<String, Object> params){
        super(params);
    }

    @Override
    protected void doValidate() throws Exception {
        id = Long.parseLong(params.get("founderid").toString());
        page = Integer.parseInt(params.get("page").toString());
        status = Byte.parseByte(params.get("status").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        Criteria criteria = session.createCriteria(Item.class)
            .setFirstResult(page * PAGE_SIZE)
            .setMaxResults(PAGE_SIZE);

        if (status != 0) {
            criteria.add(Restrictions.eq("status", status));
        }
        if (id != 0) {
            criteria.add(Restrictions.eq("founder.id", id));
        }
        response.setData(criteria.list().toArray());
    }

}
