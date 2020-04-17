package com.qianxun.qxtag.controllers.area;

import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "/area")
public class AreaController {

    @Autowired
    private TransactionService service;

    @RequestMapping("/province")
    @ResponseBody
    public Response query(@RequestBody Map<String, Object> params) {

        QueryProvinceHandler queryProvinceHandler = new QueryProvinceHandler(params);
        Response response = service.handle(queryProvinceHandler);

        return response;

    }

    @RequestMapping("/city")
    @ResponseBody
    public Response create(@RequestBody Map<String, Object> params) {

        QueryCityHandler queryCityHandler = new QueryCityHandler(params);
        Response response = service.handle(queryCityHandler);
        return response;

    }


}
