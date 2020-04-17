package com.qianxun.qxtag.controllers.account;

import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.TransactionService;
import com.qianxun.qxtag.models.persist.Admin;
import com.qianxun.qxtag.models.persist.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private TransactionService service;

    @RequestMapping(value = "/login", method=RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestBody Map<String,Object> params) {

        LoginHandler loginHandler = new LoginHandler(params, Admin.class);
        Response response = service.handle(loginHandler);

        return response;

    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    @ResponseBody
    public Response register(@RequestBody Map<String,Object> params) {

        RegisterHandler registerHandler = new RegisterHandler(params, Admin.class);
        Response response = service.handle(registerHandler);

        return response;

    }

    @RequestMapping("/query")
    @ResponseBody
    public Response query(@RequestParam Map<String, Object> params) {
        QueryHandler queryHandler = new QueryHandler(params, Admin.class);
        return service.handle(queryHandler);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response updateProfile(@RequestBody User user) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", user);

        ProfileUpdateHandler profileUpdateHandler = new ProfileUpdateHandler(map, Admin.class);
        return service.handle(profileUpdateHandler);
    }

    @RequestMapping("/pwd")
    @ResponseBody
    public Response resetPasswordByOldPassword(@RequestBody Map<String, Object> params) {
        ResetPasswordByOldHandler resetPasswordByOldHandler = new ResetPasswordByOldHandler(params, Admin.class);
        return service.handle(resetPasswordByOldHandler);
    }

    @RequestMapping("/resetbyicode")
    @ResponseBody
    public Response resetPasswordByICode(@RequestBody Map<String, Object> params) {
        ResetPasswordByICodeHandler resetPasswordByICodeHandler =
                new ResetPasswordByICodeHandler(params, Admin.class);
        return service.handle(resetPasswordByICodeHandler);
    }

    @RequestMapping("/resetalipay")
    @ResponseBody
    public Response resetAlipay(@RequestBody Map<String, Object> params) {
        ResetAlipayHandler resetAlipayHandler = new ResetAlipayHandler(params);
        return service.handle(resetAlipayHandler);
    }

    @RequestMapping("/icode")
    @ResponseBody
    public Response getICode(@RequestBody Map<String, Object> params) {
        GetICodeHandler getICodeHandler = new GetICodeHandler(params, Admin.class);
        return service.handle(getICodeHandler);
    }
}
