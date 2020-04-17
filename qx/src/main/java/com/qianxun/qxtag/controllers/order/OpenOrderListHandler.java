package com.qianxun.qxtag.controllers.order;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.persist.Order;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by eyingsh on 12/21/2016.
 */
public class OpenOrderListHandler extends BaseHandler {

    private long adminid;
    private byte status;
    private int page;

    OpenOrderListHandler(Map<String, Object> params){
        super(params);
    }

    @Override
    protected void doValidate() throws Exception {
        adminid = Long.parseLong(params.get("adminid").toString());
        page = Integer.parseInt(params.get("page").toString());
        status = Byte.parseByte(params.get("status").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        Criteria criteria = session.createCriteria(Order.class)
            .add(Restrictions.eq("status", status))
            .setFirstResult(page * PAGE_SIZE)
            .setMaxResults(PAGE_SIZE);

        response.setData(criteria.list().toArray());

    }

}
