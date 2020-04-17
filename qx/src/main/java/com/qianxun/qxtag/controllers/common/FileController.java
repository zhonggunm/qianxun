package com.qianxun.qxtag.controllers.common;

import com.qianxun.qxtag.QianxunTagConfiguration;
import com.qianxun.qxtag.controllers.Response;
import com.qianxun.qxtag.controllers.ResultCode;
import com.qianxun.qxtag.utils.ImageUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by eyingsh on 1/1/2017.
 */
@Controller
@RequestMapping(value = "/file")
public class FileController implements EnvironmentAware {

    String imagePath = "./";

    @RequestMapping(value="/upload", method=RequestMethod.GET)
    @ResponseBody
    public Response provideUploadInfo() {
        return new Response(ResultCode.NetworkRestUnsupported,
                "You can upload a file by posting to this same URL.");
    }

    /**
     * Handles File upload of volumedata
     *
     * @param file Must not be null
     **/
    @RequestMapping(value = "/upload", consumes = "multipart/form-data",
        method = RequestMethod.POST)
    @ResponseBody
    public Response handleFileUpload(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam("file") MultipartFile file) {
        Response myResponse = new Response();
        if (file != null && !file.isEmpty()) {
            // Generate file name
            String[] splitName = file.getOriginalFilename().split("\\.");
            StringBuffer tempname = new StringBuffer (splitName[0] +
                new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()));
            for(int i = 1; i < splitName.length;i++){
                tempname.append("."+splitName[i]);
            }
            String fname = tempname.toString();

            try{
                String result = copyStream(file.getInputStream(),
                    new FileOutputStream(imagePath + QianxunTagConfiguration.ITEM_IMAGE_PATH + fname));
                if (result.isEmpty()) {
                    myResponse.setCode(ResultCode.NoError);
                    myResponse.setData(fname);
                } else {
                    myResponse.setCode(ResultCode.FileCommonError);
                    myResponse.setData(result);
                }
            }catch (IOException e){
                myResponse.setCode(ResultCode.FileCommonError);
                myResponse.setData(e.getStackTrace().toString());
            }
        } else {
            myResponse.setCode(ResultCode.FileCommonError);
            myResponse.setData("Empty file");
        }
        return myResponse;
    }

    /**
     * type: image type
     *       0 - normal
     *       1 - small screen
     */
    @RequestMapping("/image/accessory")
    @ResponseBody
    public Response getAdvertismentImage(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam String filename, @RequestParam int type) {
        return sendImage(response, QianxunTagConfiguration.ACCESSORY_IMAGE_PATH, filename, type);
    }

    @RequestMapping("/image/item")
    @ResponseBody
    public Response getItemImage(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam String filename, @RequestParam int type) {
        return sendImage(response, QianxunTagConfiguration.ITEM_IMAGE_PATH, filename, type);
    }

    /**
     * type: image type
     * 0 - normal
     * 1 - small screen
     */
    @RequestMapping("/image/head")
    @ResponseBody
    public Response getHeadImage(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam String filename, @RequestParam int type) {
        return sendImage(response, QianxunTagConfiguration.HEAD_IMAGE_PATH, filename, type);
    }

    /**
     * type: image type
     * 0 - normal
     * 1 - small screen
     */
    @RequestMapping("/image/cover")
    @ResponseBody
    public Response getCoverImage(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam String filename, @RequestParam int type) {
        return sendImage(response, QianxunTagConfiguration.COVER_IMAGE_PATH, filename, type);
    }

    /**
     * type: image type
     * 0 - normal
     * 1 - small screen
     */
    @RequestMapping("/image/misc")
    @ResponseBody
    public Response getMiscImage(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam String filename, @RequestParam int type) {
        return sendImage(response, QianxunTagConfiguration.MISC_IMAGE_PATH, filename, type);
    }

    public String copyStream(InputStream in, OutputStream out){

        String result = "";
        try{
            IOUtils.copy(in, out);
        } catch (IOException e){
            result = e.getStackTrace().toString();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (IOException e) {
                //Do nothing
            }
        }

        return result;
    }

    public Response sendImage(HttpServletResponse httpResponse, String path, String filename, int type){

        Response response = new Response();

        /* workaround: compare the file name to "undefined" as well */
        if (filename.isEmpty() || filename.compareTo("undefined") == 0) {
            response.setCode(ResultCode.FileCommonError);
            response.setData("Empty file");
        }else{
            File file = new File(imagePath + path + ImageUtil.getNameForImage(type, filename));
            if (!file.exists()) {
                file = new File(imagePath + path + filename);
            }

            try {
                httpResponse.setContentType("image/gif");
                String result = copyStream(new FileInputStream(file), httpResponse.getOutputStream());
                if(result.isEmpty()){
                    response.setCode(ResultCode.NoError);
                }else{
                    response.setCode(ResultCode.FileCommonError);
                    response.setData(result);
                }
            } catch (IOException e) {
                response.setCode(ResultCode.FileCommonError);
                response.setData(e.getStackTrace().toString());
            }
        }
        return response;

    }

    @Override
    public void setEnvironment(Environment environment) {
        if (environment.getProperty("spring.profiles.active").compareTo("prod") == 0){
            imagePath = QianxunTagConfiguration.IMAGE_FILE_SYSTEM;
        }
    }

}
