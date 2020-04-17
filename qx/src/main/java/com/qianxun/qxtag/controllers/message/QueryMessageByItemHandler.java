package com.qianxun.qxtag.controllers.message;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.persist.Message;
import com.qianxun.qxtag.controllers.BaseHandler;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by eyingsh on 12/31/2016.
 */
public class QueryMessageByItemHandler extends BaseHandler {

    private long itemid = 0;
    private int page = 0;

    QueryMessageByItemHandler(Map<String, Object> params){
        super(params);
    }

    @Override
    protected void doValidate() throws Exception {
        itemid = Long.parseLong(params.get("itemid").toString());
        page = Integer.parseInt(params.get("page").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException {

        List<Serializable> list = session.createCriteria(Message.class)
                .add(Restrictions.eq( "item.id", itemid))
                .setFirstResult(page * PAGE_SIZE)
                .setMaxResults(PAGE_SIZE)
                .list();
        response.setData(list.toArray());
    }
}
