package com.qianxun.qxtag.controllers.order;

import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.persist.*;
import com.qianxun.qxtag.controllers.BaseHandler;
import org.hibernate.Session;

import java.util.List;
import java.util.Map;

/**
 * Created by yshaoxp on 2016/12/29.
 */
public class NewOrderHandler extends BaseHandler {

    long ownerid;
    int guestArea, total;
    boolean setDefault;
    String guestName, guestMobile, guestAddress;
    List<Map> maps;

    public NewOrderHandler(Map<String, Object> params){
        super(params);
        enableTransaction = true;
    }

    @Override
    protected void doValidate() throws Exception {
        ownerid = Long.parseLong(params.get("ownerid").toString());
        total = Integer.parseInt(params.get("total").toString());
        guestArea = Integer.parseInt(params.get("guestArea").toString());
        guestName = params.get("guestName").toString();
        guestMobile = params.get("guestMobile").toString();
        guestAddress = params.get("guestAddress").toString();
        setDefault = Boolean.parseBoolean(params.get("setDefault").toString());
        maps = (List<Map>) params.get("orderItems");
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        Order order = new Order();
        order.setTotal(total);
        order.setAmount(maps.size());
        order.setStatus((byte) 1);
        order.setOwner(new User(ownerid));
        order.setGuestName(guestName);
        order.setGuestMobile(guestMobile);
        order.setGuestAddress(guestAddress);
        if (guestArea != 0) {
            order.setGuestArea(new Area(guestArea));
        }
        session.save(order);

        if (setDefault){
            int rows = session.createQuery("update User set area.id=:areaid,address=:address where id=:uid")
                .setParameter("areaid", guestArea)
                .setParameter("address", guestAddress)
                .setParameter("uid", ownerid)
                .executeUpdate();
            if (rows != 1) {
                throw new QianxunException(ResultCode.DatabaseCommonError, getClass().getName());
            }
        }

        for (Map map : maps) {

            long accid, attachedItemid;
            int price, amount;
            try{
                accid = Long.parseLong(map.get("accid").toString());
                price = Integer.parseInt(map.get("price").toString());
                attachedItemid = Long.parseLong(map.get("itemid").toString());
                amount = Integer.parseInt(map.get("amount").toString());
            }catch (Exception e){
                throw new QianxunException(ResultCode.CommonInvalidParameter, e.getCause().toString());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItem(new Item(attachedItemid));
            orderItem.setAccessory(new Accessory(accid));
            orderItem.setPrice(price);
            orderItem.setAmount(amount);
            session.save(orderItem);
        }

        response.setData(order);
    }
}
