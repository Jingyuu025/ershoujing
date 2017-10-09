package com.jingyuu.ershoujing.common.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 文件类型工具
 *
 * @version 2015-12-09-10:48
 * @since [产品/模块版本]
 */
public class FileTypeUtil {
    private FileTypeUtil() {
    }
    /**
     * Created on 2010-7-1
     * <p>Discription:[getFileByFile,获取文件类型,包括图片,若格式不是已配置的,则返回null]</p>
     *
     * @param file
     * @return fileType
     * @author:[shixing_11@sina.com]
     */
    public final static FileTypeEnum getFileByFile(File file) {
        FileTypeEnum filetype = null;
        byte[] b = new byte[50];
        try {
            InputStream is = new FileInputStream(file);
            is.read(b);
            filetype = getFileTypeByStream(b);
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filetype;
    }

    /**
     * 获取文件类型
     *
     * @param b
     * @return
     */
    public final static FileTypeEnum getFileTypeByStream(byte[] b) {
        byte[] temp = null;
        if (b.length > 50) {
            temp = new byte[50];
            System.arraycopy(b, 0, temp, 0, 50);
        } else {
            temp = b;
        }
        temp = b;
        String filetypeHex = String.valueOf(getFileHexString(temp)).toUpperCase();

        FileTypeEnum[] fileTypes = FileTypeEnum.values();

        for (FileTypeEnum type : fileTypes) {
            if (filetypeHex.startsWith(type.getHexRefixion())) {
                return type;
            }
        }
        return null;
    }

    /**
     * Created on 2010-7-2
     * <p>Discription:[isImage,判断文件是否为图片]</p>
     *
     * @param file
     * @return true 是 | false 否
     * @author:[shixing_11@sina.com]
     */
    public static final boolean isImage(File file) {
        boolean flag = false;
        try {
            BufferedImage bufreader = ImageIO.read(file);
            int width = bufreader.getWidth();
            int height = bufreader.getHeight();
            if (width == 0 || height == 0) {
                flag = false;
            } else {
                flag = true;
            }
        } catch (IOException e) {
            flag = false;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * Created on 2010-7-1
     * <p>Discription:[getFileHexString]</p>
     *
     * @param b
     * @return fileTypeHex
     * @author:[shixing_11@sina.com]
     */
    public final static String getFileHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (b == null || b.length <= 0) {
            return null;
        }
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

}
