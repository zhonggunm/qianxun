package com.qianxun.qxtag.controllers.order;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.persist.OrderItem;
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
public class OrderDetailHandler extends BaseHandler {

    long orderid;

    public OrderDetailHandler(Map<String, Object> params){
        super(params);
    }

    @Override
    protected void doValidate() throws Exception {
        orderid = Long.parseLong(params.get("orderid").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        Criteria criteria = session.createCriteria(OrderItem.class)
            .add(Restrictions.eq("order.id", orderid));
        response.setData(criteria.list().toArray());

    }

}
