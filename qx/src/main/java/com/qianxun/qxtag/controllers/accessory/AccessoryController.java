package com.qianxun.qxtag.controllers.accessory;

import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.TransactionService;
import com.qianxun.qxtag.controllers.account.ProfileUpdateHandler;
import com.qianxun.qxtag.models.persist.Admin;
import com.qianxun.qxtag.models.persist.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/accessory")
public class AccessoryController {

    @Autowired
    private TransactionService service;

    @RequestMapping("/list")
    @ResponseBody
    public Response queryList(@RequestBody Map<String,Object> params) {
        QueryHandler queryHandler = new QueryHandler(params);
        return service.handle(queryHandler);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Response newItem(@RequestBody Map<String, Object> params) {

        NewAccessoryHandler newAccessoryHandler = new NewAccessoryHandler(params);
        return service.handle(newAccessoryHandler);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response updateProfile(@RequestBody User user) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user",user);
        ProfileUpdateHandler profileUpdateHandler = new ProfileUpdateHandler(params, Admin.class);
        return service.handle(profileUpdateHandler);
    }

}
