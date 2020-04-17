package com.qianxun.qxtag.controllers.item;

import com.qianxun.qxtag.QianxunTagConfiguration;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "/item")
public class ItemController implements EnvironmentAware {

    String imagePath = "./";

    @Autowired
    private TransactionService service;

    @RequestMapping("/list")
    @ResponseBody
    public Response query(@RequestBody Map<String,Object> params) {
        QueryByOwnerHandler queryHandler = new QueryByOwnerHandler(params);
        return service.handle(queryHandler);
    }

    @RequestMapping("/list/byfounder")
    @ResponseBody
    public Response listByFounder(@RequestBody Map<String, Object> params) {
        QueryByFounderHandler queryByFounderHandler = new QueryByFounderHandler(params);
        return service.handle(queryByFounderHandler);
    }

    @RequestMapping("/list/id")
    @ResponseBody
    public Response listById(@RequestBody Map<String, Object> params) {
        QueryByIdHandler queryByIdHandler = new QueryByIdHandler(params);
        return service.handle(queryByIdHandler);
    }

    @RequestMapping("/lost")
    @ResponseBody
    public Response lost(@RequestBody Map<String, Object> params) {
        LostReportHandler lostReportHandler = new LostReportHandler(params);
        return service.handle(lostReportHandler);
    }

    @RequestMapping("/cancellost")
    @ResponseBody
    public Response cancelLost(@RequestBody Map<String, Object> params) {
        ItemStatusChangeHandler lostReportHandler = new ItemStatusChangeHandler(params, (byte)2);
        return service.handle(lostReportHandler);
    }

    @RequestMapping("/found")
    @ResponseBody
    public Response found(@RequestBody Map<String, Object> params) {
        FoundReportHandler foundReportHandler = new FoundReportHandler(params);
        return service.handle(foundReportHandler);
    }

    @RequestMapping(value = "/attach", method = RequestMethod.POST)
    @ResponseBody
    public Response attach(@RequestBody Map<String, Object> params) {
        AttachHandler attachHandler = new AttachHandler(params);
        return service.handle(attachHandler);
    }

    @RequestMapping(value = "/accept", method = RequestMethod.POST)
    @ResponseBody
    public Response accept(@RequestBody Map<String, Object> params) {
        AcceptHandler acceptHandler = new AcceptHandler(params);
        return service.handle(acceptHandler);
    }
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Response newItem(@RequestBody Map<String, Object> params) {

        NewItemHandler newItemHandler = new NewItemHandler(params);
        newItemHandler.setImagePath(imagePath);
        return service.handle(newItemHandler);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response updateProfile(@RequestBody Map<String, Object> params) {

        UpdateHandler updateHandler = new UpdateHandler(params);
        return service.handle(updateHandler);
    }

    @RequestMapping(value = "/prepay", method = RequestMethod.POST)
    @ResponseBody
    public Response prepay(@RequestBody Map<String, Object> params) {
        PrepayHandler prepayHandler = new PrepayHandler(params);
        return service.handle(prepayHandler);
    }

    @RequestMapping(value = "/reword/new", method = RequestMethod.POST)
    @ResponseBody
    public Response newReward(@RequestBody Map<String, Object> params) {
        NewRewardHandler newRewardHandler = new NewRewardHandler(params);
        return service.handle(newRewardHandler);
    }

    @RequestMapping(value = "/handover", method = RequestMethod.POST)
    @ResponseBody
    public Response handover(@RequestBody Map<String, Object> params) {
        HandoverHandler handoverHandler = new HandoverHandler(params);
        return service.handle(handoverHandler);
    }

    @RequestMapping(value = "/reserve", method = RequestMethod.POST)
    @ResponseBody
    public Response reserve(@RequestBody Map<String, Object> params) {
        IDReserveHandler idReserveHandler = new IDReserveHandler(params);
        return service.handle(idReserveHandler);
    }

    @RequestMapping(value = "/unregister", method = RequestMethod.POST)
    @ResponseBody
    public Response unregister(@RequestBody Map<String, Object> params) {
        ItemStatusChangeHandler unregisterHandler = new ItemStatusChangeHandler(params, (byte) 10);
        return service.handle(unregisterHandler);
    }

    @RequestMapping(value = "/set/founder", method = RequestMethod.POST)
    @ResponseBody
    public Response setFounder(@RequestBody Map<String, Object> params) {
        SetFounderHandler setFounderHandler = new SetFounderHandler(params);
        return service.handle(setFounderHandler);
    }

    @Override
    public void setEnvironment(Environment environment) {
        if (environment.getProperty("spring.profiles.active").compareTo("prod") == 0) {
            imagePath = QianxunTagConfiguration.IMAGE_FILE_SYSTEM;
        }
    }
}
