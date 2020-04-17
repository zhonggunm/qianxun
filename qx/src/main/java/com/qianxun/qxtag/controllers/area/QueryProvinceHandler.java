package com.qianxun.qxtag.controllers.area;

import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.models.persist.Area;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by eyingsh on 12/31/2016.
 */
public class QueryProvinceHandler extends BaseHandler {

    public QueryProvinceHandler(Map<String, Object> params){
        super(params);
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        List<Serializable> list = session.createCriteria(Area.class)
            .add(Restrictions.gt("id", 0))
            .add(Restrictions.eq("pid", 0))
            .list();

        response.setData(list.toArray());

    }
}
