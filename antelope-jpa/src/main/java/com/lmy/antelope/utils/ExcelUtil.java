package com.lmy.antelope.utils;

import com.alibaba.excel.ExcelReader;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author yangmeiliang
 * @date 2018/10/18
 */
public class ExcelUtil {


    public static void read(MultipartFile multipartFile) throws Exception{
        InputStream inputStream = multipartFile.getInputStream();

    }
}
