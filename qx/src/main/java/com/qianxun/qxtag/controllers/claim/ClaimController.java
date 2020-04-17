package com.qianxun.qxtag.controllers.claim;

import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "/claim")
public class ClaimController {

    @Autowired
    private TransactionService service;

    @RequestMapping("/query")
    @ResponseBody
    public Response query(@RequestBody Map<String, Object> params) {

        QueryMyClaimHandler queryMyClaimHandler = new QueryMyClaimHandler(params);
        Response response = service.handle(queryMyClaimHandler);

        return response;

    }

    @RequestMapping("/create")
    @ResponseBody
    public Response create(@RequestBody Map<String, Object> params) {

        CreateClaimHandler createClaimHandler = new CreateClaimHandler(params);
        Response response = service.handle(createClaimHandler);

        return response;

    }


}
