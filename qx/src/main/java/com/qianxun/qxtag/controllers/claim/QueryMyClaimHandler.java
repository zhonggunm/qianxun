package com.qianxun.qxtag.controllers.claim;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.persist.Claim;
import com.qianxun.qxtag.controllers.BaseHandler;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by eyingsh on 12/31/2016.
 */
public class QueryMyClaimHandler extends BaseHandler {

    private long uid = 0;
    private int page = 0;

    QueryMyClaimHandler(Map<String, Object> params){
        super(params);
    }

    @Override
    protected void doValidate() throws Exception {
        uid = Long.parseLong(params.get("uid").toString());
        page = Integer.parseInt(params.get("page").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        List<Serializable> list = session.createCriteria(Claim.class)
                .add(Restrictions.eq("user.id", uid))
                .setFirstResult(page * PAGE_SIZE)
                .setMaxResults(PAGE_SIZE)
                .list();

        response.setData(list.toArray());
    }
}
