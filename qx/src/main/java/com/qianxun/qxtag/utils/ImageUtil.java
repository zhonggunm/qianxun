package com.qianxun.qxtag.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtil {

    public final static int SIZE_LARGE = 480;
    public final static int SIZE_MIDDLE = 160;
    public final static int SIZE_SMALL = 80;

    public static String buildAllImages(String path, String file) {

        String ret = "";

        try {
            BufferedImage src = ImageIO.read(new File(path + file));
            int width = src.getWidth(), height = src.getHeight();
            double ratio;

            if(width * height > SIZE_LARGE * SIZE_LARGE){
                ratio = width * 1.0 / SIZE_MIDDLE;
                resizeImageTo(path, src, getNameForImage(SIZE_MIDDLE,file),
                    (int)(width/ratio),(int)(height/ratio));

                ratio = width * 1.0 / SIZE_SMALL;
                resizeImageTo(path, src, getNameForImage(SIZE_SMALL, file),
                    (int) (width / ratio), (int) (height / ratio));

            } else if (width * height > SIZE_MIDDLE * SIZE_MIDDLE) {

                ratio = width * 1.0 / SIZE_SMALL;
                resizeImageTo(path, src, getNameForImage(SIZE_SMALL, file),
                    (int) (width / ratio), (int) (height / ratio));

            }

        }catch (IOException e){
            ret = e.getMessage() + ":" + path + file;
        }
        return  ret;
    }

    public static String getNameForImage(int size, String name){
        String newname;

        if(size == SIZE_LARGE || size == 0){
            newname = name;
        } else {
            String[] splitName = name.split("\\.");
            StringBuffer tempname = new StringBuffer(splitName[0]);
            String tag;
            switch (size){
                case SIZE_MIDDLE:
                    tag = "m";
                    break;
                case SIZE_SMALL:
                    tag = "s";
                    break;
                default:
                    /* unreachable */
                    tag = "";
                    break;
            }
            tempname.append(tag);
            for (int i = 1; i < splitName.length; i++) {
                tempname.append("." + splitName[i]);
            }
            newname = tempname.toString();
        }
        return newname;
    }

    public static void resizeImageTo(String path, BufferedImage srcImagePath, String toImagePath,
                                     int width, int height) throws IOException{
        BufferedImage targetImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        targetImage.getGraphics().drawImage(srcImagePath, 0, 0, width, height, null);

        ImageIO.write(targetImage, toImagePath.substring(toImagePath.lastIndexOf(".")+1),
            new File(path + toImagePath));
    }

}
