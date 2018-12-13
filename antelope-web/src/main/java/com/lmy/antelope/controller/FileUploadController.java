package com.lmy.antelope.controller;

import com.lmy.antelope.core.domain.Result;
import com.lmy.antelope.util.FileUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author yangmeiliang
 * @date 2018/10/23
 */
@RestController
@RequestMapping("/upload/")
public class FileUploadController {

    @PostMapping("/test")
    public Result upload(@RequestParam MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            File tempFile = FileUtil.generateTempFile(file.getBytes(), fileName);
            return Result.success().setItem(tempFile.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success().setItem(fileName);
    }
}
