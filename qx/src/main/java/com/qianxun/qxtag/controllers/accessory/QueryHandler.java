package com.qianxun.qxtag.controllers.accessory;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.models.persist.Accessory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Map;

/**
 * Created by eyingsh on 12/21/2016.
 */
public class QueryHandler extends BaseHandler {

    short type;
    int page, instock;

    public QueryHandler(Map<String, Object> params){
        super(params);
    }

    @Override
    protected void doValidate() throws Exception {
        type = Short.parseShort(params.get("type").toString());
        page = Integer.parseInt(params.get("page").toString());
        instock = Integer.parseInt(params.get("stock").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        Criteria criteria = session.createCriteria(Accessory.class)
            .setFirstResult(page * PAGE_SIZE)
            .setMaxResults(PAGE_SIZE);
        if (type != 0){
            criteria.add(Restrictions.eq("type.id", type));
        }
        if (instock != 0) {
            criteria.add(Restrictions.gt("instock", 0));
        }
        response.setData(criteria.list().toArray());
    }

}
