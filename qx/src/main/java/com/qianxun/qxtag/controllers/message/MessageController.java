package com.qianxun.qxtag.controllers.message;

import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "/message")
public class MessageController {

    @Autowired
    private TransactionService service;

    @RequestMapping("/list/byuser")
    @ResponseBody
    public Response queryMyMessage(@RequestBody Map<String, Object> params) {

        QueryMessageByUserHandler queryMyMessageHandler = new QueryMessageByUserHandler(params);
        Response response = service.handle(queryMyMessageHandler);

        return response;

    }

    @RequestMapping("/list/byitem")
    @ResponseBody
    public Response queryByItem(@RequestBody Map<String, Object> params) {

        QueryMessageByItemHandler queryMessageByItemHandler = new QueryMessageByItemHandler(params);
        Response response = service.handle(queryMessageByItemHandler);

        return response;

    }

    @RequestMapping("/create")
    @ResponseBody
    public Response create(@RequestBody Map<String, Object> params) {

        CreateMessageHandler createMessageHandler = new CreateMessageHandler(params);
        Response response = service.handle(createMessageHandler);

        return response;

    }


}
