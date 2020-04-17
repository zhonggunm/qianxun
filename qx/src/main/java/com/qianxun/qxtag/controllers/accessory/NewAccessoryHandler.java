package com.qianxun.qxtag.controllers.accessory;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.BaseHandler;
import org.hibernate.Session;

import java.util.Map;

/**
 * Created by yshaoxp on 2016/12/29.
 */
public class NewAccessoryHandler extends BaseHandler {

    public NewAccessoryHandler(Map<String, Object> params) {
        super(params);
    }

    @Override
    public void prepare(Session session, Response response) throws QianxunException {}

    @Override
    public void exec(Session session, Response response) throws QianxunException{}
}
