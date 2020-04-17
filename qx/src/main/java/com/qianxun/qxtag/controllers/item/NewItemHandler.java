package com.qianxun.qxtag.controllers.item;

import com.qianxun.qxtag.QianxunTagConfiguration;
import com.qianxun.qxtag.controllers.QianxunException;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.models.persist.Item;
import com.qianxun.qxtag.controllers.BaseHandler;
import com.qianxun.qxtag.utils.ImageUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by yshaoxp on 2016/12/29.
 */
public class NewItemHandler extends BaseHandler{

    private String imagePath;

    private long itemid, ownerid, accessoryid;
    private byte category;
    private int areaid;
    private String name, image;
    Item item;

    NewItemHandler(Map<String, Object> params) {
        super(params);
        this.accessoryid = 1;
        this.areaid = 1;
        enableTransaction = true;
    }

    void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    @Override
    protected void doValidate() throws Exception {
        itemid = Long.parseLong(params.get("itemid").toString());
        ownerid = Long.parseLong(params.get("userid").toString());
        name = params.get("name").toString();
        image = params.get("image").toString();
        category = Byte.parseByte(params.get("category").toString());
    }

    @Override
    public void exec(Session session, Response response) throws QianxunException{

        if (itemid == 0) {
            List<Serializable> list = session.createCriteria(Item.class)
                .add(Restrictions.eq("status", (byte) 1))
                .setMaxResults(1)
                .list();

            if(list.size() == 1) {
                item = (Item) list.get(0);
                itemid = item.getId();
            } else {
                throw new QianxunException(ResultCode.DatabaseNotFound, "No reserved items found");
            }
        } else {
            item = new Item();
            item.setId(itemid);
        }

        String hql = "update Item set name=:name,image=:image,status=:status," +
            "category=:category,area.id=:area,accessory.id=:accessory,owner.id=:ownerid " +
            "where id=:itemid";

        int rows = session.createQuery(hql)
            .setParameter("category", category)
            .setParameter("name", name)
            .setParameter("image", image)
            .setParameter("accessory", accessoryid)
            .setParameter("area", areaid)
            .setParameter("ownerid", ownerid)
            .setParameter("itemid", itemid)
            .setParameter("status", (byte) 2)
            .executeUpdate();
        if (rows != 1) {
            throw new QianxunException(ResultCode.DatabaseUpdateError, getClass().getName());
        }

    }

    @Override
    public void finish(Session session, Response response) throws QianxunException{

        if(image.compareTo("") != 0) {
            String ret = ImageUtil.buildAllImages(imagePath + QianxunTagConfiguration.ITEM_IMAGE_PATH, image);
            if (ret.compareTo("") != 0) {
                response.setResponse(ResultCode.FileCommonError, ret);
            } else {
                response.setData(item);
            }
        } else {
            response.setData(item);
        }
    }

}
