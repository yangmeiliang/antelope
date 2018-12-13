package com.lmy.antelope.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * @author yangmeiliang
 * @date 2018/6/8
 */
public final class FileUtil {

    @Getter
    @AllArgsConstructor
    public enum Suffix {

        TXT("txt"),
        PNG("png"),
        XLS("xls"),
        ;

        private String value;
    }

    public static BufferedImage getBufferedImageFromNetByUrl(String strUrl) {
        if (StringUtils.isBlank(strUrl)) {
            return null;
        }
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new URL(strUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bufferedImage;
    }

    /**
     * 生成临时文件
     *
     * @param bufferedImage BufferedImage
     * @param filePrefix    文件名前缀
     * @param fileSuffix    文件扩展名
     * @return 临时文件
     */
    public static File generateTempFile(BufferedImage bufferedImage, String filePrefix, Suffix fileSuffix) {
        File file = null;
        try {
            file = File.createTempFile(filePrefix, "." + fileSuffix.getValue());
            ImageIO.write(bufferedImage, fileSuffix.getValue(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static File generateTempFile(byte[] fileBytes, String filePrefix, Suffix fileSuffix) {
        File file = null;
        try {
            file = File.createTempFile(filePrefix, "." + fileSuffix.getValue());
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(fileBytes);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static File generateTempFile(byte[] fileBytes, String fileName) {
        File file = null;
        try {
            String filePrefix = fileName.substring(0, fileName.lastIndexOf("."));
            String fileSuffix = fileName.replace(filePrefix, "");
            file = File.createTempFile(filePrefix, fileSuffix);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(fileBytes);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
