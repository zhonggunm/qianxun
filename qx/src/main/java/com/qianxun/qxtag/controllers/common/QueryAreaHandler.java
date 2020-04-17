package com.qianxun.qxtag.controllers.common;

import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.persist.Area;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by eyingsh on 12/31/2016.
 */
public class QueryAreaHandler extends BaseHandler {

    private int pid = 0;

    public QueryAreaHandler(Map<String, Object> params){
        super(params);
    }

    @Override
    protected void doValidate() throws Exception {
        pid = Integer.parseInt(params.get("pid").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException {

        List<Serializable> list = session.createCriteria(Area.class)
                .add(Restrictions.eq("pid", pid))
                .list();
        response.setData(list.toArray());
    }
}
