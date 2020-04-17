package com.qianxun.qxtag.controllers.account;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.persist.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by yshaoxp on 2016/12/29.
 */
public class LoginByWXHandler extends BaseHandler {

    String appid,secret,code;
    JsonNode root;

    public LoginByWXHandler(Map<String, Object> params) {
        super(params);
        enableTransaction = true;
    }

    @Override
    protected void doValidate() throws Exception {
        appid = params.get("appid").toString();
        secret = params.get("secret").toString();
        code = params.get("code").toString();
    }

    @Override
    protected void prepare(Session session, Response response) throws QianxunException {

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
            "appid=" + appid +
            "&secret=" + secret +
            "&code=" + code +
            "&grant_type=authorization_code";
        try {
            root = (new ObjectMapper()).readTree(
                (new RestTemplate()).getForObject(url, String.class));

            url = "https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" + root.get("access_token").asText() +
                "&openid=" + root.get("openid").asText();
            root = (new ObjectMapper()).readTree(
                (new RestTemplate()).getForObject(url, String.class));
        } catch (Exception e) {
            throw new QianxunException(ResultCode.ThirdpartyCommonError, e.getCause().toString());
        }
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        String unionid = root.get("unionid").asText();

        List<Serializable> list = session.createCriteria(clazz)
            .add(Restrictions.eq("wxunionid", unionid))
            .list();

        if (list.size() == 0) {
            User user = new User();
            user.setWxunionid(unionid);
            // No need to set acc.id anymore.
            session.save(user);
            list.add(user);
        }

        User user = (User)list.get(0);
        user.setNickname(root.get("nickname").asText());
        user.setSex((byte)root.get("sex").asInt());
        user.setHeadimgurl(root.get("headimgurl").asText());

        response.setData(list.get(0));

    }

}
