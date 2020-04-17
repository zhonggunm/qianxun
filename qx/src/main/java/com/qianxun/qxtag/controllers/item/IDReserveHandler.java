package com.qianxun.qxtag.controllers.item;

import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.CommonMessage;
import com.qianxun.qxtag.models.persist.Item;
import org.hibernate.Session;

import java.util.Map;

/**
 * Created by yshaoxp on 2016/12/29.
 */
public class IDReserveHandler extends BaseHandler {

    int count = 0;

    public IDReserveHandler(Map<String, Object> params) {
        super(params);
        enableTransaction = true;
    }

    @Override
    protected void doValidate() throws Exception {
        count = Integer.parseInt(params.get("count").toString());
        if (count < 10) {
            throw new QianxunException(ResultCode.CommonInvalidParameter, "too little");
        }
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        long min = 0, max = 0;

        min = (long) session.save(new Item());
        for (int i = 1; i < count-1; i++){
            session.save(new Item());
        }
        max = (long) session.save(new Item());

        response.setData(new CommonMessage(min + "," + max));

    }

}
