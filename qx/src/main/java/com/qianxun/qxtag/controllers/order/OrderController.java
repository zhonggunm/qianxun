package com.qianxun.qxtag.controllers.order;

import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.account.ProfileUpdateHandler;
import com.qianxun.qxtag.controllers.item.AttachHandler;
import com.qianxun.qxtag.controllers.TransactionService;
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
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private TransactionService service;

    @RequestMapping("/list")
    @ResponseBody
    public Response query(@RequestBody Map<String,Object> params) {

        OrderListHandler orderListHandler = new OrderListHandler(params);
        return service.handle(orderListHandler);
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Response detail(@RequestBody Map<String, Object> params) {

        OrderDetailHandler orderDetailHandler = new OrderDetailHandler(params);
        return service.handle(orderDetailHandler);
    }

    @RequestMapping(value = "/attach", method = RequestMethod.POST)
    @ResponseBody
    public Response attach(@RequestBody Map<String, Object> params) {

        AttachHandler attachHandler = new AttachHandler(params);
        return service.handle(attachHandler);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Response newItem(@RequestBody Map<String, Object> params) {

        NewOrderHandler newOrderHandler = new NewOrderHandler(params);
        return service.handle(newOrderHandler);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response updateProfile(@RequestBody User user) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", user);
        ProfileUpdateHandler profileUpdateHandler = new ProfileUpdateHandler(params, User.class);
        return service.handle(profileUpdateHandler);
    }

    @RequestMapping("/list/open")
    @ResponseBody
    public Response queryOpenList(@RequestBody Map<String, Object> params) {

        OpenOrderListHandler openOrderListHandler = new OpenOrderListHandler(params);
        return service.handle(openOrderListHandler);
    }

    @RequestMapping("/deliver")
    @ResponseBody
    public Response deliver(@RequestBody Map<String, Object> params) {

        OrderDeliverHandler orderDeliverHandler = new OrderDeliverHandler(params);
        return service.handle(orderDeliverHandler);
    }

    @RequestMapping("/workflow")
    @ResponseBody
    public Response process(@RequestBody Map<String, Object> params) {

        WorkFlowHandler workFlowHandler = new WorkFlowHandler(params);
        return service.handle(workFlowHandler);
    }

}
