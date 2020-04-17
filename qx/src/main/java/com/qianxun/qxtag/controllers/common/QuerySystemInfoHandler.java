package com.qianxun.qxtag.controllers.common;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.models.Sysinfo;
import com.qianxun.qxtag.controllers.BaseHandler;
import org.hibernate.Session;

import java.util.Map;

/**
 * Created by eyingsh on 12/31/2016.
 */
public class QuerySystemInfoHandler extends BaseHandler {

    public QuerySystemInfoHandler(Map<String, Object> params){
        super(params);
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        Sysinfo sysinfo = new Sysinfo();
        sysinfo.setApiversion((short)1);
        response.setData(sysinfo);

    }
}
