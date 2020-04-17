package com.qianxun.qxtag.controllers.common;

import com.qianxun.qxtag.QianxunTagConfiguration;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.TransactionService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Controller
public class SystemRootController implements EnvironmentAware {

    String imagePath = "./";

    @Autowired
    private TransactionService service;

    @RequestMapping("/info")
    @ResponseBody
    public Response querySystemInfo(@RequestBody Map<String, Object> params) {

        QuerySystemInfoHandler queryAPIVersionHandler = new QuerySystemInfoHandler(params);
        Response response = service.handle(queryAPIVersionHandler);

        return response;

    }

    @RequestMapping("/poll")
    @ResponseBody
    public Response queryPollInfo(@RequestBody Map<String, Object> params) {

        QueryPollInfoHandler queryPollInfoHandler = new QueryPollInfoHandler(params);
        Response response = service.handle(queryPollInfoHandler);

        return response;
    }

    @RequestMapping(value = "/qx", method = RequestMethod.GET)
    public void getFile(HttpServletRequest request,
                        HttpServletResponse response) {

        String path = imagePath + QianxunTagConfiguration.APP_IMAGE_PATH;
        String[] filelist = new File(path).list();
        if(filelist.length == 1){
            File file = new File(path + filelist[0]);
            FileInputStream in = null;
            OutputStream out = null;

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Length", String.valueOf(file.length()));
            try {
                response.setHeader("Content-disposition", "attachment; filename="
                    + new String(filelist[0].getBytes("utf-8"), "ISO8859-1"));
                out = response.getOutputStream();
                in = new FileInputStream(file);
                IOUtils.copy(in, out);
                response.flushBuffer();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) in.close();
                    if (out != null) out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        if (environment.getProperty("spring.profiles.active").compareTo("prod") == 0){
            imagePath = QianxunTagConfiguration.IMAGE_FILE_SYSTEM;
        }
    }

}
