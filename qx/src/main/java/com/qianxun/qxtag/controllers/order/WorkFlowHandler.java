package com.qianxun.qxtag.controllers.order;

import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import org.hibernate.Session;

import java.util.Map;

public class WorkFlowHandler extends BaseHandler {

    long id;
    byte status;
    String data;

    public WorkFlowHandler(Map<String, Object> params) {
        super(params);
    }

    @Override
    protected void doValidate() throws Exception {
        id = Long.parseLong(params.get("id").toString());
        status = Byte.parseByte(params.get("status").toString());
        data = params.get("data").toString();
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        String properties = "";
        switch (status) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                properties = ",payment='" + data + "'";
                break;
            default:
                break;
        }

        String hql = "update Order set status=:status" + properties + " where id=:id";

        int rows = session.createQuery(hql)
            .setParameter("status", status)
            .setParameter("id", id)
            .executeUpdate();
        if (rows != 1) {
            throw new QianxunException(ResultCode.DatabaseCommonError, this.getClass().getName() + "step2");
        }else {
            response.setData("" + id);
        }
    }
}
