package com.qianxun.qxtag.controllers.account;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.models.persist.ICode;
import com.qianxun.qxtag.utils.QCloudSMSUtil;
import com.qianxun.qxtag.controllers.BaseHandler;
import org.hibernate.Session;

import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * Created by yshaoxp on 2017/5/31.
 */
public class GetICodeHandler extends BaseHandler {

    String mobile,icode;

    public GetICodeHandler(Map<String, Object> params, Class clazz) {
        super(params, clazz);
    }

    @Override
    protected void doValidate() throws Exception {
        mobile = params.get("mobile").toString();
    }

    @Override
    protected void prepare(Session session, Response response) throws QianxunException {
        int r = Math.abs(new Random(new Date().getTime()).nextInt()) % 10000;
        while (r < 1000) r *= 10;
        icode = "" + r;
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{
        session.saveOrUpdate(new ICode(mobile, icode));
    }

    @Override
    public void finish(Session session, Response response) throws QianxunException{
        QCloudSMSUtil.sendSms(QCloudSMSUtil.QCLOUD_VCODE_TEMPLETE, mobile, icode);
    }

}
